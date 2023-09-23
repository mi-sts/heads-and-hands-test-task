package creatures

class Player(data: CreatureData) : Creature(data) {
    companion object {
        private const val MaxHealingsNumber = 4

        private const val HealthPerHealingPercentage = 30
    }

    private var remainingHealingsNumber: Int = MaxHealingsNumber

    private fun useHealing() {
        if (remainingHealingsNumber == 0) {
            println("Player ($this) has no healings anymore!")
            return
        }

        val healingHealth = calculateHealingHealth(this)
        health += healingHealth

        println("Player ($this) use a healing.")
        println("Health after a healing: $health")
    }

    protected fun calculateHealingHealth(healingCreature: Creature): Int =
        (healingCreature.maxHealth * (HealthPerHealingPercentage / 100f)).toInt()
}