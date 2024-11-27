// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SubShooter;

public class Tilt extends Command {
  private DoubleSupplier tiltSpeed;
  private SubShooter subShooter;

  /** Creates a new Tilt. */
  public Tilt(DoubleSupplier tiltSpeed, SubShooter subShooter) {
    this.tiltSpeed = tiltSpeed;
    this.subShooter = subShooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subShooter.tiltShooters(
      tiltSpeed.getAsDouble()
    );
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
