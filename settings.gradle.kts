rootProject.name = "FightMC"
includeFlat("library", "fight", "bukkit-view")

for (project in rootProject.children) {
    project.projectDir = file("modules/${project.name}")
}
