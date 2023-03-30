


package sos.arch.coalition;

import sos.arch.coalition.ae.FireStreamsCoordination;
import sos.arch.coalition.cs.ECU;
import sos.arch.coalition.cs.IoT_LGS;
import sos.arch.coalition.cs.IoT_TSI;
import sos.arch.coalition.constraintshare.ConstraintShareData;
import sos.arch.coalition.constraintshare.StochasticConstraints;

public class FMER_SoS implements DEVS_SoS {

  val double Trate1
  val double Trate2
  val double Trate3
  val double Trate4
  val double Trate5

  val FireStreamsCoordination fsc = new FireStreamsCoordination()
  val IoT_TSI iot_tsi = new IoT_TSI()
  val IoT_LGS iot_lgs = new IoT_LGS()
  val ECU ecu = new ECU()

  override def init() {
    // Add necessary code for initialization here
  }

  override def externalInput(port: String, value: Object) {
    // Add necessary code for handling external input here
  }

  override def internalStateTransition() {
    // Add necessary code for internal state transition here
  }

  override def externalOutput(port: String): Object {
    // Add necessary code for handling external output here
  }

  override def timeAdvance(): Double {
    // Add necessary code for calculating time advance here
  }

  @Override
  SoS_Arch_Caoliton getStructure() {
    val structure = new SoS_Arch_Caoliton()
    structure.FMER_SoS(this.Trate1, this.Trate2, this.Trate3, this.Trate4, this.Trate5)

    structure.hasAE(this.fsc)
    structure.hasCS(this.iot_tsi)
    structure.hasCS(this.iot_lgs)
    structure.hasCS(this.ecu)

    this.fsc.bindWith(this.iot_tsi, "Sends", "Firestream", this.Trate1, this.ecu)
    this.fsc.bindWith(this.iot_lgs, "Sends", "WarningsMessages", this.Trate2, this.ecu)

    val sc = new StochasticConstraints()
    sc.If("FireStreams")
    sc.Tell(ConstraintShareData.Trate1)
    sc.Ask(ConstraintShareData.Trate1)
    structure.behaviors.add(sc)

    return structure
  }

}
