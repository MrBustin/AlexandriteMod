package net.justin.alexandritemod.entity.custom;

import net.justin.alexandritemod.entity.ModEntities;
import net.justin.alexandritemod.entity.TriceratopsVariant;
import net.justin.alexandritemod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TriceratopsEntity extends Animal {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(TriceratopsEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(TriceratopsEntity.class, EntityDataSerializers.INT);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;



    public TriceratopsEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this,1.0D,true));

        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(ModItems.KOHLRABI.get()), false));

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1,new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,30D)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.FOLLOW_RANGE,24D)
                .add(Attributes.ATTACK_DAMAGE,50D)
                .add(Attributes.ATTACK_KNOCKBACK,1D)
                .add(Attributes.ATTACK_SPEED,4D);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.KOHLRABI.get());
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.TRICERATOPS.get().create(pLevel);
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0){
            attackAnimationTimeout = 40;
            attackAnimationState.start(this.tickCount);
        }else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    public void setAttacking(boolean attacking){
        this.entityData.set(ATTACKING,attacking);
    }

    public boolean isAttacking(){
        return this.entityData.get(ATTACKING);
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity pEntity) {
        return this.getAttackBoundingBox().inflate(1.5d).intersects(getHitbox(pEntity));
    }

    private static AABB getHitbox(LivingEntity entity) {
        AABB aabb = entity.getBoundingBox();
        Entity vehicle = entity.getVehicle();
        if (vehicle != null) {
            Vec3 vec3 = vehicle.getPassengerRidingPosition(entity);
            return aabb.setMinY(Math.max(vec3.y, aabb.minY));
        } else {
            return aabb;
        }
    }



    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    /* VARIANT */
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(VARIANT, 0);
        pBuilder.define(ATTACKING, false);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public TriceratopsVariant getVariant() {
        return TriceratopsVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(TriceratopsVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(VARIANT, pCompound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty,
                                        MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        TriceratopsVariant variant = Util.getRandom(TriceratopsVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    @Override
    public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @Nullable AgeableMob pBaby) {
        TriceratopsVariant variant = Util.getRandom(TriceratopsVariant.values(), this.random);
        ((TriceratopsEntity) pBaby).setVariant(variant);
        super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
    }

    // Sounds

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAMEL_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNIFFER_DEATH;
    }
}

