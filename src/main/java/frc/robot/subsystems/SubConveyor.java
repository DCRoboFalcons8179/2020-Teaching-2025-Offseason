// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.lib.math.Filter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SubConveyor extends SubsystemBase {
  /** Creates a new SubConveyor. */
  
  TalonFX beaterBar = new TalonFX(Constants.ConveyorConstants.beaterBarID);
  VictorSPX conveyorMotor = new VictorSPX(Constants.ConveyorConstants.conveyorMotorID); 
  
  public SubConveyor() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveConveyor(double power) {
    beaterBar.set(Filter.cutoffFilter(power));
    conveyorMotor.set(VictorSPXControlMode.PercentOutput, Filter.cutoffFilter(power));
  }
}
