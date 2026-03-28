# Detail: src\main\resources\bundles

## 这一层在做什么
这一层是 PGMM 本地化文本的真实源头层，是所有用户可见设置名称、描述、提示语和窗口标题的来源。

## 这一层直接包含什么
### 直接子目录
- 本层没有直接子目录。

### 直接文件
- bundle.properties（6.78 KB）
- bundle_be.properties（3.08 KB）
- bundle_bg.properties（3.08 KB）
- bundle_ca.properties（3.08 KB）
- bundle_cs.properties（3.08 KB）
- bundle_da.properties（3.08 KB）
- bundle_de.properties（3.08 KB）
- bundle_es.properties（3.08 KB）
- bundle_et.properties（3.08 KB）
- bundle_eu.properties（3.08 KB）
- bundle_fi.properties（3.08 KB）
- bundle_fil.properties（3.08 KB）
- bundle_fr.properties（3.08 KB）
- bundle_hu.properties（3.08 KB）
- bundle_id_ID.properties（3.08 KB）
- bundle_it.properties（3.08 KB）
- bundle_ja.properties（3.08 KB）
- bundle_ko.properties（3.08 KB）
- bundle_lt.properties（3.08 KB）
- bundle_nl.properties（3.08 KB）
- bundle_nl_BE.properties（3.08 KB）
- bundle_pl.properties（3.08 KB）
- bundle_pt_BR.properties（3.08 KB）
- bundle_pt_PT.properties（3.08 KB）
- bundle_ro.properties（3.08 KB）
- bundle_ru.properties（3.08 KB）
- bundle_sr.properties（3.08 KB）
- bundle_sv.properties（3.08 KB）
- bundle_th.properties（3.08 KB）
- bundle_tk.properties（3.08 KB）
- bundle_tr.properties（3.08 KB）
- bundle_uk_UA.properties（3.08 KB）
- bundle_vi.properties（3.08 KB）
- bundle_zh_CN.properties（7.03 KB）
- bundle_zh_TW.properties（3.08 KB）

## 这一层如何实现自己的职责
这里有一整套 bundle.properties 及各语言变体。设置页的分组标题、toast 文本、救援提示、Power Table 标题、OverlayUI 窗口显示名等都通过这些键值驱动。源码里遵循“用户可见文案走 bundle，不在 Java 硬编码”的约束，因此功能逻辑和显示文本保持了解耦。

## 这一层与其他层级的关系
它向上服务 src/main/java/powergridminimap，向下被复制到 build/resources/main/bundles、build/release-stage/bundles、bin/main/bundles 和最终归档。

## 阅读这一层时要注意什么
对多语言 Mindustry 模组来说，这层的重要性几乎和 Java 源码同级。新增功能若不补 bundle，问题会立刻暴露。
