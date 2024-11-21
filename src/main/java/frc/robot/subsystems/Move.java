// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.math.Filter;
import frc.robot.Constants;

public class Move extends SubsystemBase {
  VictorSPX leftFollower = new VictorSPX(Constants.DriveConstants.leftFollowerID);
  VictorSPX rightFollower = new VictorSPX(Constants.DriveConstants.rightFollowerID);
  TalonSRX leftDrive = new TalonSRX(Constants.DriveConstants.leftDriveID);
  TalonSRX rightDrive = new TalonSRX(Constants.DriveConstants.rightDriveID);
  /** Creates a new Move. */
  public Move() {

    leftDrive.setInverted(Constants.DriveConstants.leftDriveInverted);
    leftFollower.setInverted(Constants.DriveConstants.leftFollowerInverted);

    rightDrive.setInverted(Constants.DriveConstants.rightDriveInverted);
    rightFollower.setInverted(Constants.DriveConstants.rightFollowerInverted);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void driveRobot(double speedPower, double turnPower) {
    leftDrive.set(TalonSRXControlMode.PercentOutput, Filter.cutoffFilter(speedPower - turnPower));
    rightDrive.set(TalonSRXControlMode.PercentOutput, Filter.cutoffFilter(speedPower + turnPower));
    
    leftFollower.follow(leftDrive);
    rightFollower.follow(rightDrive);
    SmartDashboard.putNumber("Speed", speedPower);
    SmartDashboard.putNumber("Turn", turnPower);
    
  }
}
