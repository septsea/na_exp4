package na4                                                                                                                                     ;

import org.apache.commons.math3.linear.LUDecomposition                                                                                          ;
import org.apache.commons.math3.linear.MatrixUtils                                                                                              ;
import org.apache.commons.math3.linear.RealMatrix                                                                                               ;

public class Test                                                                                                                               {
    public static void main(final String... args)                                                                                               {
        // Problem (1)
        System.out.println("Problem (1):\n")                                                                                                    ;
        final double[][] A1 = { { 3.01, 6.03, 1.99 }, { 1.27, 4.16, -1.23 }, { 0.987, -4.81, 9.34 } }                                           ;
        final double[] b1 = { 1, 1, 1 }                                                                                                         ;
        final double[][] A2 = { { 10, -7, 0, 1 }, { -3, 2.099999, 6, 2 }, { 5, -1, 5, -1 }, { 2, 1, 0, 2 } }                                    ;
        final double[] b2 = { 8, 5.900001, 5, 1 }                                                                                               ;
        Methods.GaussianElimination(true, true, A1, b1)                                                                                         ;
        System.out.println("\n====================================\n")                                                                          ;
        Methods.solveByPLU(A1, b1)                                                                                                              ;
        System.out.println("\n====================================\n")                                                                          ;
        Methods.GaussianElimination(true, true, A2, b2)                                                                                         ;
        System.out.println("\n====================================\n")                                                                          ;
        Methods.solveByPLU(A2, b2)                                                                                                              ;
        System.out.println()                                                                                                                    ;
        // Problem (2)
        System.out.println("Problem (2):\n")                                                                                                    ;
        final double[][] A3 = { { 3.00, 6.03, 1.99 }, { 1.27, 4.16, -1.23 }, { 0.990, -4.81, 9.34 } }                                           ;
        final double[] b3 = { 1, 1, 1 }                                                                                                         ;
        Methods.GaussianElimination(true, true, A3, b1)                                                                                         ;
        System.out.println()                                                                                                                    ;
        // Problem (3)
        System.out.println("Problem (3):\n")                                                                                                    ;
        final double[][] A4 = { { 10, -7, 0, 1 }, { -3, 2.1, 6, 2 }, { 5, -1, 5, -1 }, { 2, 1, 0, 2 } }                                         ;
        final double[] b4 = { 8, 5.900001, 5, 1 }                                                                                               ;
        Methods.GaussianElimination(true, true, A4, b4)                                                                                         ;
        System.out.println()                                                                                                                    ;
        // Problem (4)
        System.out.println("Problem (4):\n")                                                                                                    ;
        final RealMatrix M1 = MatrixUtils.createRealMatrix(A1)                                                                                  ;
        final RealMatrix M1Inv = new LUDecomposition(M1).getSolver().getInverse()                                                               ;
        final RealMatrix M2 = MatrixUtils.createRealMatrix(A2)                                                                                  ;
        final RealMatrix M2Inv = new LUDecomposition(M2).getSolver().getInverse()                                                               ;
        final RealMatrix M3 = MatrixUtils.createRealMatrix(A3)                                                                                  ;
        final RealMatrix M3Inv = new LUDecomposition(M3).getSolver().getInverse()                                                               ;
        final RealMatrix M4 = MatrixUtils.createRealMatrix(A4)                                                                                  ;
        final RealMatrix M4Inv = new LUDecomposition(M4).getSolver().getInverse()                                                               ;
        final RealMatrix c1 = MatrixUtils.createColumnRealMatrix(b1)                                                                            ;
        final RealMatrix c2 = MatrixUtils.createColumnRealMatrix(b2)                                                                            ;
        final RealMatrix c3 = MatrixUtils.createColumnRealMatrix(b3)                                                                            ;
        final RealMatrix c4 = MatrixUtils.createColumnRealMatrix(b4)                                                                            ;
        System.out.println("x1 = A1^(-1) * b1 = ")                                                                                              ;
        System.out.println(M1Inv.multiply(c1))                                                                                                  ;
        System.out.println()                                                                                                                    ;
        System.out.println("x2 = A2^(-1) * b2 = ")                                                                                              ;
        System.out.println(M2Inv.multiply(c2))                                                                                                  ;
        System.out.println()                                                                                                                    ;
        System.out.println("x3 = A3^(-1) * b3 = ")                                                                                              ;
        System.out.println(M3Inv.multiply(c3))                                                                                                  ;
        System.out.println()                                                                                                                    ;
        System.out.println("x4 = A4^(-1) * b4 = ")                                                                                              ;
        System.out.println(M4Inv.multiply(c4))                                                                                                  ;
        System.out.println()                                                                                                                    ;
        System.out.println("det A1 = " + new LUDecomposition(M1).getDeterminant())                                                              ;
        System.out.println("det A2 = " + new LUDecomposition(M2).getDeterminant())                                                              ;
        System.out.println("det A3 = " + new LUDecomposition(M3).getDeterminant())                                                              ;
        System.out.println("det A4 = " + new LUDecomposition(M4).getDeterminant())                                                              ;}}
