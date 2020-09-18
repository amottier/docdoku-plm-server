/*
 * DocDoku, Professional Open Source
 * Copyright 2006 - 2020 DocDoku SARL
 *
 * This file is part of DocDokuPLM.
 *
 * DocDokuPLM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DocDokuPLM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with DocDokuPLM.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.docdoku.plm.server.core.product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Represents a CAD instance of a specific part defined in a
 * {@link PartUsageLink} {@link PartSubstituteLink}.
 *
 * By using its attributes: translation and orientation on the three axis
 * it will be possible to position the mesh to render it.
 *
 * @author Florent Garin
 * @version 1.1, 20/07/12
 * @since   V1.1
 */
@Table(name="CADINSTANCE")
@Entity
public class CADInstance implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;


    /**
     * Translation on x axis.
     */
    private double tx;

    /**
     * Translation on y axis.
     */
    private double ty;

    /**
     * Translation on z axis.
     */
    private double tz;

    /**
     * Radian orientation on x axis.
     */
    private double rx;

    /**
     * Radian orientation on y axis.
     */
    private double ry;

    /**
     * Radian orientation on z axis.
     */
    private double rz;

    /**
     * Rotation matrix
     */
    @Embedded
    private RotationMatrix rotationMatrix;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RotationType rotationType;


    public CADInstance() {
    }

    public CADInstance(double tx, double ty, double tz, double rx, double ry, double rz) {
        this.tx = tx;
        this.ty = ty;
        this.tz = tz;
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        rotationType = RotationType.ANGLE;
        this.rotationMatrix = new RotationMatrix(new double[9]);
    }

    public CADInstance(RotationMatrix rotationMatrix, double tx, double ty, double tz) {
        rotationType = RotationType.MATRIX;
        this.tx = tx;
        this.ty = ty;
        this.tz = tz;
        this.rotationMatrix = rotationMatrix;
    }


    public double getRx() {
        return rx;
    }

    public double getRy() {
        return ry;
    }

    public double getRz() {
        return rz;
    }

    public double getTx() {
        return tx;
    }

    public double getTy() {
        return ty;
    }

    public double getTz() {
        return tz;
    }

    public void setRx(double rx) {
        this.rx = rx;
    }

    public void setRy(double ry) {
        this.ry = ry;
    }

    public void setRz(double rz) {
        this.rz = rz;
    }

    public void setTx(double tx) {
        this.tx = tx;
    }

    public void setTy(double ty) {
        this.ty = ty;
    }

    public void setTz(double tz) {
        this.tz = tz;
    }

    public RotationMatrix getRotationMatrix() {
        return rotationMatrix;
    }

    public void setRotationMatrix(RotationMatrix rotationMatrix) {
        this.rotationMatrix = rotationMatrix;
    }

    public RotationType getRotationType() {
        return rotationType;
    }

    public void setRotationType(RotationType rotationType) {
        this.rotationType = rotationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public CADInstance clone() {
        CADInstance clone;
        try {
            clone = (CADInstance) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return clone;
    }

}
