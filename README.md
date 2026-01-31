# 电网小地图 / Power Grid Minimap（Mindustry Mod）

在小地图/大地图上为每个电网分别着色，并在每个电网中心显示电力盈亏；支持电网断开告警与建议连接点。

Color each disconnected power network on the minimap/full map and show its power balance at the grid center; includes split alerts and reconnect hints.

## 安装 / Install

- 直接下载并安装：前往 GitHub Releases 下载 `powergrid-minimap.zip` 并导入游戏。
- Download from Releases: get `powergrid-minimap.zip` from GitHub Releases and import it in-game.

## 构建 / Build (for developers)

此仓库只包含模组源码；要从源码打包 zip，需要把该项目放进一份 Mindustry 源码仓库里（例如放到 `Mindustry-master/mods/powergrid-minimap`），并在 Mindustry 的 `settings.gradle` 中 include 该模块，然后在 Mindustry 仓库根目录执行：

```powershell
./gradlew.bat :powergrid-minimap:zipMod
```

输出 zip 在 `mods/powergrid-minimap/build/libs/`。

This repo contains only the mod source. To build a zip from source, place it under a Mindustry source checkout (e.g. `Mindustry-master/mods/powergrid-minimap`), include it in Mindustry's `settings.gradle`, then run the command above in the Mindustry repo root.
