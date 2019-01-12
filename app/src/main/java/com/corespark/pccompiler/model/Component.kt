package com.corespark.pccompiler.model

import com.corespark.pccompiler.R
import com.parse.ParseClassName


/**
 * @author Zachy Bazov.
 * @since 11/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
open class Component {

    @ParseClassName("CPU")
    data class CPU(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_cpu }

    @ParseClassName("Cooler")
    data class Cooler(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_cooler }

    @ParseClassName("Motherboard")
    data class Motherboard(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var paramD: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_motherboard }

    @ParseClassName("Memory")
    data class Memory(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var paramD: String,
        var paramE: String,
        var paramF: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_memory }

    @ParseClassName("Storage")
    data class Storage(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var paramD: String,
        var paramE: String,
        var paramF: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_storage }

    @ParseClassName("ExternalStorage")
    data class ExternalStorage(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_extstorage }

    @ParseClassName("OpticalDrive")
    data class OpticalDrive(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var paramD: String,
        var paramE: String,
        var paramF: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_optdrive }

    @ParseClassName("GraphicCard")
    data class GraphicCard(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var paramD: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_graphiccard }

    @ParseClassName("SoundCard")
    data class SoundCard(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var paramD: String,
        var paramE: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_soundcard }

    @ParseClassName("PowerSupply")
    data class PowerSupply(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var paramD: String,
        var paramE: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_powersupply }

    @ParseClassName("Case")
    data class Case(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var paramD: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_case }

    @ParseClassName("OS")
    data class OperatingSystem(
        var id: String,
        var manufaturer: String,
        var component: String,
        var price: String
    ) : Component() { val image = R.mipmap.ic_opsystem }

}