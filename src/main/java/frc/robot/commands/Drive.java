// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Move;

public class Drive extends Command {
  private DoubleSupplier speedPower;
  private DoubleSupplier turnPower;
  private Move move;


  /** Creates a new Drive. */
  public Drive(DoubleSupplier speedPower, DoubleSupplier turnPower, Move move) {
    this.speedPower = speedPower;
    this.turnPower = turnPower;
    this.move = move;

    addRequirements(move);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    move.driveRobot(speedPower.getAsDouble() * Constants.DriveSettings.maxSpeed, turnPower.getAsDouble());
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
