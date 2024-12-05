// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SubShooter;
import java.util.function.BooleanSupplier;
import frc.robot.subsystems.SubConveyor;

public class SmartShoot extends Command {
  private SubShooter subShooter;
  private SubConveyor subConveyor;
  private BooleanSupplier topButton;
  /** Creates a new SmartShoot. */
  public SmartShoot(SubShooter subShooter, BooleanSupplier topButton, SubConveyor subConveyor) {
    this.subShooter = subShooter;
    this.topButton = topButton;
    this.subConveyor= subConveyor;

    System.out.println("I was called for the first time");

    addRequirements(subConveyor, subShooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double velocity = subShooter.getVelocity();

    SmartDashboard.putNumber("Velcoity", velocity);

    new Shoot(() -> topButton.getAsBoolean() ? 1 : 0, subShooter);

    if (velocity >= 34000 && !topButton.getAsBoolean()){
      subShooter.driveShooters(1);
      subConveyor.driveConveyor(
        1
      );
    }
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
