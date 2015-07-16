package Game;

public class EntityProjectile extends Entity {
	// Only exists so EntityAbilityProjectile and EntityAbilityProjectileVector are related
	int playerHealthMod, enemyHealthMod, effectIDEnemy, effectIDPlayer, AOEID, targets;
	boolean hasEffect, spawnsAOE;
	Entity owner;
}
