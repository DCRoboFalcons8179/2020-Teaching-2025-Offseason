// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SubConveyor;

public class Conveyor extends Command {
  private DoubleSupplier power;
  private SubConveyor subConveyor;
  /** Creates a new Conveyor. */
  public Conveyor(DoubleSupplier power, SubConveyor subConveyor) {
    this.power = power;
    this.subConveyor = subConveyor;

    addRequirements(subConveyor);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subConveyor.driveConveyor(
      power.getAsDouble()
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
