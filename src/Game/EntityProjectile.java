package Game;

public class EntityProjectile extends Entity {
	// Only exists so EntityAbilityProjectile and EntityAbilityProjectileVector are related
	int healthMod, effectID;
	boolean hasEffect, targetsEnemy, spawnsAOE;
}
