package pe.edu.utp.proyecto_poo.plugins.scala

import pe.edu.utp.proyecto_poo.api.{Strategy, Context, Result}
import pe.edu.utp.proyecto_poo.domain.{EnergyInput, EnergyResult}

class AdaptiveEnergyStrategy extends Strategy[EnergyInput, EnergyResult] {

  override def id(): String = "adaptive-scala"

  override def supports(context: Context): Boolean = {
    context != null && context.getLoad() >= 40.0 && context.getLoad() <= 75.0
  }

  override def execute(input: EnergyInput): Result[EnergyResult] = {
    val energyResult = new EnergyResult("Adaptive Mode (Scala): Dynamic frequency scaling applied.")
    new Result[EnergyResult](true, energyResult)
  }
}
