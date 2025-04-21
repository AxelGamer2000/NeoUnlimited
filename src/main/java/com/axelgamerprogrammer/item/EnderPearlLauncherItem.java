package com.axelgamerprogrammer.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class EnderPearlLauncherItem extends BowItem {
    public EnderPearlLauncherItem(Properties p_40660_) {
        super(p_40660_);
    }
    @Override
    protected void shootProjectile(LivingEntity p_331372_, Projectile p_332000_, int p_330631_, float p_331251_, float p_331199_, float p_330857_, @Nullable LivingEntity p_331572_) {
        p_332000_ = new ThrownEnderpearl(p_331372_.level(), p_331372_, new ItemStack(Items.ENDER_PEARL));
        p_332000_.shootFromRotation(p_331372_, p_331372_.getXRot(), p_331372_.getYRot() + p_330857_, 0.0F, p_331251_, p_331199_);
        p_331372_.level().addFreshEntity(p_332000_);
    }

    @Override
    public boolean releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
        if (p_40669_ instanceof Player player) {
            ItemStack itemstack = new ItemStack(Items.ENDER_PEARL);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                int i = this.getUseDuration(p_40667_, p_40669_) - p_40670_;
                i = EventHooks.onArrowLoose(p_40667_, p_40668_, player, i, !itemstack.isEmpty());
                if (i < 0) {
                    return false;
                } else {
                    float f = getPowerForTime(i);
                    if ((double)f < 0.1) {
                        return false;
                    } else {
                        List<ItemStack> list = draw(p_40667_, itemstack, player);
                        if (p_40668_ instanceof ServerLevel) {
                            ServerLevel serverlevel = (ServerLevel)p_40668_;
                            if (!list.isEmpty()) {
                                this.shoot(serverlevel, player, player.getUsedItemHand(), p_40667_, list, f * 3.0F, 1.0F, f == 1.0F, (LivingEntity)null);
                            }
                        }

                        p_40668_.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (p_40668_.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                        player.awardStat(Stats.ITEM_USED.get(this));
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return itemStack -> itemStack.getItem() == Items.ENDER_PEARL;
    }

    @Override
    public ItemStack getDefaultCreativeAmmo(@Nullable Player player, ItemStack projectileWeaponItem) {
        return Items.ENDER_PEARL.getDefaultInstance();
    }
}
