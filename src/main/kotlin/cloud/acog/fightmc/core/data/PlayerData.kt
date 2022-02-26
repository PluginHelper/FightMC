package cloud.acog.fightmc.core.data

import java.util.UUID

open class PlayerData(
    val uuid: UUID,
    val winScore: Int,
    val failScore: Int
) {
    constructor(uuid: UUID) : this(uuid, 0, 0)
}