package creatures

abstract class Creature(data: CreatureData) {
    val maxHealth: Int = data.health

    var health: Int = maxHealth
        protected set(value) {
            field = value.coerceIn(0, maxHealth)

            checkIfDie()
        }
    var attack: Int = data.attack
        protected set(value) {
            field = value.coerceIn(1, MaxAttackValue)

            if (value !in 1..MaxAttackValue)
                println("The assigning attack value ($value) is out of the allowed range(1..$MaxAttackValue)!")
        }
    var defense: Int = data.defence
        protected set(value) {
            field = value.coerceIn(1, MaxDefenseValue)

            if (value !in 1..MaxDefenseValue)
                println("The assigning defense value ($value) is out of the allowed range(1..$MaxDefenseValue)!")
        }
    var damageRange: IntRange = data.damageRange
        private set

    private var isDied: Boolean = false

    init {
        if (data.attack !in 1..MaxAttackValue ||
            data.defence !in 1..MaxDefenseValue ||
            data.health < 0 ||
            data.damageRange.first <= 0 || data.damageRange.last <= 0
        ) {
            println("Creating creature parameters is out of the allowed range!")
        }

        println("Created creature ($this)")
        checkIfDie()
    }

    fun attack(otherCreature: Creature) {
        if (!isAttackSuccessful(this, otherCreature)) {
            println("Attack of creature ($this) was not successful.")
            return
        }

        println("Creature ($this) attacks the creature ($otherCreature).")
        val attackDamage = damageRange.random()
        otherCreature.takeDamage(attackDamage)
    }

    fun takeDamage(damage: Int) {
        if (isDied) {
            println("Died creature ($this) cannot be attacked!")
            return
        }

        health -= damage
        println("Creature ($this) took $damage damage. Remaining health: $health.")
    }

    protected fun die() {
        if (isDied) {
            println("Creature ($this) is already dead!")
            return
        }

        isDied = true
        println("Creature ($this) died.")
    }

    private fun checkIfDie() {
        if (health <= 0)
            die()
    }

    companion object {
        private const val MaxAttackValue = 30
        private const val MaxDefenseValue = 30

        private val attackSuccessfulDiceRollsValues = listOf(5, 6)

        protected fun calculateAttackModifier(attackedCreature: Creature, attackingCreature: Creature): Int =
            attackingCreature.attack - attackedCreature.defense + 1

        protected fun isAttackSuccessful(attackingCreature: Creature, attackedCreature: Creature): Boolean {
            val attackModifier = calculateAttackModifier(attackedCreature, attackingCreature)
            val diceRollsValues = List(attackModifier) { rollDice() }

            return diceRollsValues.any { attackSuccessfulDiceRollsValues.contains(it) }
        }

        protected fun rollDice(): Int = (1..6).random()
    }
}
