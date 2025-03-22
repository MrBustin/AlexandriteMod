package net.justin.alexandritemod.entity.custom;

import net.justin.alexandritemod.entity.ModEntities;
import net.justin.alexandritemod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;



public class BeaverEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState shakeAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private boolean wasInWater = false;
    private int dryOffTicks = 0; // Timer for the animation


    public BeaverEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(Items.STICK), true));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,30D)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.FOLLOW_RANGE,24D)
                .add(Attributes.OXYGEN_BONUS,100D)
                .add(Attributes.WATER_MOVEMENT_EFFICIENCY,100D);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.STICK);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.BEAVER.get().create(pLevel);
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()){
            this.setupAnimationStates();
        }
        boolean isInWaterNow = this.isInWater();

        // Check if the beaver was in water but now is not
        if (wasInWater && !isInWaterNow && !this.isInWaterRainOrBubble()) {
            playDryOffAnimation();
        }

        // Update tracking variable ONLY if actually in water
        wasInWater = isInWaterNow;

        // Decrease dry-off timer
        if (dryOffTicks > 0) {
            dryOffTicks--;
        }

    }

    private void playDryOffAnimation() {
        this.dryOffTicks = 40; // Adjust animation duration as needed
        this.shakeAnimationState.start(this.tickCount);

        if (this.level().isClientSide()) {
            spawnWaterParticles(); // Spawn water droplets
        }// Trigger shake animation
    }

    private void spawnWaterParticles() {
        for (int i = 0; i < 20; i++) { // Spawn multiple particles
            double offsetX = (this.random.nextDouble() - 0.5) * this.getBbWidth(); // Spread around entity
            double offsetY = this.random.nextDouble() * this.getBbHeight(); // Spread upwards
            double offsetZ = (this.random.nextDouble() - 0.5) * this.getBbWidth();

            this.level().addParticle(ParticleTypes.SPLASH,
                    this.getX() + offsetX,
                    this.getY() + offsetY,
                    this.getZ() + offsetZ,
                    0, 0.2, 0); // Slight upward motion
        }
    }

    @Override
    public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @Nullable AgeableMob pBaby) {
        super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
    }

    //Sounds
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.RABBIT_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.RABBIT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ARMADILLO_DEATH;
    }
}




