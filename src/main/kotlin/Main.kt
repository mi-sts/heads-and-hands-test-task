import creatures.Creature
import creatures.CreatureData
import creatures.Monster
import creatures.Player

fun main(args: Array<String>) {
    runTestCase()
}

private fun runTestCase() {
    val weakMonsterData = CreatureData(50, 10, 5, 2..5)
    val weakMonster = Monster(weakMonsterData)

    val strongMonsterData = CreatureData(100, 15, 10, 5..15)
    val strongMonster = Monster(strongMonsterData)

    val playerData = CreatureData(100, 20, 10, 10..20)
    val player = Player(playerData)

    weakMonster.attack(player)
    strongMonster.attack(player)

    player.attack(weakMonster)
    player.attack(strongMonster)

    strongMonster.attack(player)
    strongMonster.attack(player)
    strongMonster.attack(player)
    strongMonster.attack(player)
    strongMonster.attack(weakMonster)

    player.useHealing()
    player.attack(weakMonster)
    player.attack(weakMonster)
    player.attack(weakMonster)
    player.attack(weakMonster)

    val megaMonsterData = CreatureData(5000, 30, 30, 100..200)
    val megaMonster = Monster(megaMonsterData)

    megaMonster.attack(player)
    megaMonster.attack(strongMonster)

    player.useHealing()
}
