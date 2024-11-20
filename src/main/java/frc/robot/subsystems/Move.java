// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Move extends SubsystemBase {
  VictorSPX leftFollower = new VictorSPX(Constants.DriveSettings.leftFollowerID);
  VictorSPX rightFollower = new VictorSPX(Constants.DriveSettings.rightFollowerID);
  TalonSRX leftDrive = new TalonSRX(Constants.DriveSettings.leftDriveID);
  TalonSRX rightDrive = new TalonSRX(Constants.DriveSettings.rightDriveID);
  /** Creates a new Move. */
  public Move() {

    leftDrive.setInverted(Constants.DriveSettings.leftDriveInverted);
    leftFollower.setInverted(Constants.DriveSettings.leftFollowerInverted);

    rightDrive.setInverted(Constants.DriveSettings.rightDriveInverted);
    rightFollower.setInverted(Constants.DriveSettings.rightFollowerInverted);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void driveRobot(double speedPower, double turnPower) {
    leftDrive.set(TalonSRXControlMode.PercentOutput, speedPower);
    rightDrive.set(TalonSRXControlMode.PercentOutput, speedPower);
    leftFollower.follow(leftDrive);
    rightFollower.follow(rightDrive);

    SmartDashboard.putNumber("Speed", speedPower);
  }
}
