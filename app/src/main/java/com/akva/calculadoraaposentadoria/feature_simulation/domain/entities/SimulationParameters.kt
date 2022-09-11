package com.akva.calculadoraaposentadoria.feature_simulation.domain.entities

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class SimulationParameters(
    val initialAmount: BigDecimal,
    val monthlyContribution: BigDecimal,
    val monthlyYield: Float,
    val monthlyAppreciation: Float,
    val years: Int,
    val months: Int = years * 12,
    val isReinvestingDividends: Boolean,
    val patrimonyGoal: BigDecimal = BigDecimal.ZERO,
    val dividendsGoal: BigDecimal = BigDecimal.ZERO
): Parcelable {
    companion object NavigationType : NavType<SimulationParameters>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): SimulationParameters? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): SimulationParameters {
            return Gson().fromJson(value, SimulationParameters::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: SimulationParameters) {
            bundle.putParcelable(key, value)
        }
    }

    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}
