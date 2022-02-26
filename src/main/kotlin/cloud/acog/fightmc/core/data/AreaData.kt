package cloud.acog.fightmc.core.data

import org.bukkit.Location
import java.util.UUID

open class AreaData(
    val areaName: String,
    val creatorUUID: UUID,

    var firstPlayerUUID: UUID?,
    var secondPlayerUUID: UUID?,

    var areaTime: Int = 300,
    var state: Boolean = true,
    var managerState: Boolean = true,

    var watchPlaceLocation: Location?,
    var spawnLocation: Location?,
    var areaFirstLocation: Location?,
    var areaSecondLocation: Location?,
) {
    constructor(areaName: String, creatorUUID: UUID) : this(
        areaName, creatorUUID, null, null, 300, true, true, null, null, null, null
    )
}