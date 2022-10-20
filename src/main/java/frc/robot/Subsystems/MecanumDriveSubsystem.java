package frc.robot.Subsystems;

import java.util.function.DoubleSupplier;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MecanumDriveSubsystem extends SubsystemBase {
    
    MecanumDrive mecanumDrive;

    AHRS gyro = new AHRS();

    public MecanumDriveSubsystem(MecanumDrive mecanumDrive)
    {
        this.mecanumDrive = mecanumDrive;       
    }

    public void driveCartesian(DoubleSupplier y, DoubleSupplier x, DoubleSupplier rot)
    {
        //System.out.println(-gyro.getAngle());
        //System.out.println(rot.getAsDouble());
    
        mecanumDrive.driveCartesian(y.getAsDouble() * 0.4, x.getAsDouble() * 0.4, rot.getAsDouble() * 0.15);     
        //mecanumDrive.driveCartesian(x * 0.5, y * 0.6, rot.getAsDouble());     
        
        //mecanumDrive.driveCartesian(y.getAsDouble() * 0.6, x.getAsDouble() * 0.6, rot.getAsDouble() * 0.6, 0.0);     
        
    }
}
