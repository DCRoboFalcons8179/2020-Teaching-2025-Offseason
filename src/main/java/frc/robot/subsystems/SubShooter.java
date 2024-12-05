// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.lib.math.Filter;
import frc.robot.commands.Conveyor;
// import frc.robot.commands.Conveyor;

public class SubShooter extends SubsystemBase {

  TalonSRX rightShooter = new TalonSRX (Constants.ShooterConstants.rightShooterID);
  TalonSRX leftShooter = new TalonSRX (Constants.ShooterConstants.leftShooterID);
  TalonFX tiltMotor = new TalonFX (Constants.ShooterConstants.tiltMotorID);
  double velocity = rightShooter.getSelectedSensorVelocity();
  private final SubConveyor subConveyor = new SubConveyor();
  
  /** Creates a new SubShooter. */
  public SubShooter() {
    rightShooter.config_kP(0, Constants.ShooterConstants.kP);
    rightShooter.config_kI(0, Constants.ShooterConstants.kI);
    rightShooter.config_kD(0, Constants.ShooterConstants.kD);
    rightShooter.config_kF(0, Constants.ShooterConstants.kF);
    rightShooter.setInverted(Constants.ShooterConstants.rightShooterInverted);
    leftShooter.setInverted(Constants.ShooterConstants.leftShooterInverted);
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

  public double getVelocity() {
      return velocity;
  }
}
