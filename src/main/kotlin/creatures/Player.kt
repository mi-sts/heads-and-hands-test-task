package creatures

class Player(data: CreatureData) : Creature(data) {
    companion object {
        private const val MaxHealingsNumber = 4

        private const val HealthPerHealingPercentage = 30
    }

    private var remainingHealingsNumber: Int = MaxHealingsNumber

    fun useHealing() {
        if (isDead) {
            println("Player is dead and cannot use a healing!")
            return
        }

        if (remainingHealingsNumber == 0) {
            println("Player ($this) has no healings anymore!")
            return
        }

        val healingHealth = calculateHealingHealth(this)
        health += healingHealth

        println("Player ($this) use a healing. Current health: $health")
    }

    protected fun calculateHealingHealth(healingCreature: Creature): Int =
        (healingCreature.maxHealth * (HealthPerHealingPercentage / 100f)).toInt()
}