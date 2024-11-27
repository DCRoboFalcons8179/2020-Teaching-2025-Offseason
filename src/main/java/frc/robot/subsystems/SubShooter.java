// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.lib.math.Filter;

public class SubShooter extends SubsystemBase {

  TalonSRX rightShooter = new TalonSRX (Constants.ShooterConstants.rightShooterID);
  TalonSRX leftShooter = new TalonSRX (Constants.ShooterConstants.leftShooterID);
  TalonFX tiltMotor = new TalonFX (Constants.ShooterConstants.tiltMotorID);
  
  /** Creates a new SubShooter. */
  public SubShooter() {
    leftShooter.setInverted(Constants.ShooterConstants.leftShooterInverted);
    rightShooter.setInverted(Constants.ShooterConstants.rightShooterInverted);
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }
  public void driveShooters (double power) {
    rightShooter.set(TalonSRXControlMode.PercentOutput, Filter.cutoffFilter(power));
    leftShooter.set(TalonSRXControlMode.PercentOutput, Filter.cutoffFilter(power));
  }

  public void tiltShooters (double power) {
    tiltMotor.set(Filter.cutoffFilter(power));
  }
}
