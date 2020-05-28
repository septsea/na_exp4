package na4                                                                                                                                     ;

import java.util.HashMap                                                                                                                        ;

public class Methods                                                                                                                            {
    public static boolean isZero(final double x)                                                                                                {
        return Math.abs(x) < 1e-12                                                                                                              ;}

    public static HashMap<String, Object> GaussianElimination(final boolean isColumnPrincipalEntry,
            final boolean displayInfo, final double[][] A, final double[]... bs)                                                                {
        final int r = A.length                                                                                                                  ;
        int i = 0                                                                                                                               ;
        final Matrix aug = Matrix.augmentedMatrix(A, bs)                                                                                        ;
        if (displayInfo)                                                                                                                        {
            System.out.println("A = ")                                                                                                          ;
            System.out.println(new Matrix(A))                                                                                                   ;
            System.out.println("b = ")                                                                                                          ;
            System.out.println(new Matrix(true, bs[0]))                                                                                         ;}
        final Matrix permutation = Matrix.identity(r)                                                                                           ;
        final Matrix lower = new Matrix(r, r)                                                                                                   ;
        int detSign = 1                                                                                                                         ;
        while (i < r - 1)                                                                                                                       {
            // row swap
            if (isColumnPrincipalEntry || isZero(aug.get(i, i)))                                                                                {
                final double[] tmp1 = new double[r - i]                                                                                         ;
                int j = 0                                                                                                                       ;
                while (j < r - i)                                                                                                               {
                    tmp1[j] = aug.get(i, j - - i)                                                                                               ;
                    j -=- 1                                                                                                                     ;}
                j = 0                                                                                                                           ;
                int tmp2 = 0                                                                                                                    ;
                while (j < r - i)                                                                                                               {
                    if (Math.abs(tmp1[j]) > Math.abs(tmp1[tmp2]))                                                                               {
                        tmp2 = j                                                                                                                ;}
                    j -=- 1                                                                                                                     ;}
                final int principalIndex = tmp2 - - i                                                                                           ;
                if (i != principalIndex)                                                                                                        {
                    if (displayInfo)                                                                                                            {
                        System.out.println("Row " + i + " <-> " + "Row " + principalIndex + ":")                                                ;}
                    aug.swap(i, principalIndex)                                                                                                 ;
                    permutation.swap(i, principalIndex)                                                                                         ;
                    lower.swap(i, principalIndex)                                                                                               ;
                    detSign *= -1                                                                                                               ;
                    if (displayInfo)                                                                                                            {
                        System.out.println(aug)                                                                                                 ;}}}
            int j = i - - 1                                                                                                                     ;
            while (j < r)                                                                                                                       {
                final double m = aug.get(j, i) / aug.get(i, i)                                                                                  ;
                lower.set(j, i, m)                                                                                                              ;
                aug.mRowAdd(-m, i, j)                                                                                                           ;
                j -=- 1                                                                                                                         ;}
            if (displayInfo)                                                                                                                    {
                System.out.println(aug)                                                                                                         ;}
            i -=- 1                                                                                                                             ;}
        i = 0                                                                                                                                   ;
        while (i < r)                                                                                                                           {
            lower.set(i, i, 1)                                                                                                                  ;
            i -=- 1                                                                                                                             ;}
        final Matrix[] uppers = new Matrix[r]                                                                                                   ;
        i = 0                                                                                                                                   ;
        while (i < r)                                                                                                                           {
            uppers[i] = new Matrix(true, aug.getVector(true, i))                                                                                ;
            i -=- 1                                                                                                                             ;}
        final Matrix upper = Matrix.concatenate(true, uppers)                                                                                   ;
        final HashMap<String, Object> result = new HashMap<>()                                                                                  ;
        result.put("permutation", permutation)                                                                                                  ;
        result.put("lower", lower)                                                                                                              ;
        result.put("upper", upper)                                                                                                              ;
        double det = detSign                                                                                                                    ;
        i = 0                                                                                                                                   ;
        while (i < r)                                                                                                                           {
            det *= upper.get(i, i)                                                                                                              ;
            i -=- 1                                                                                                                             ;}
        result.put("determinant", det)                                                                                                          ;
        // backward elimination
        i = r - 1                                                                                                                               ;
        while (i > 0)                                                                                                                           {
            int j = 0                                                                                                                           ;
            while (j < i)                                                                                                                       {
                final double m = aug.get(j, i) / aug.get(i, i)                                                                                  ;
                aug.mRowAdd(-m, i, j)                                                                                                           ;
                j -=- 1                                                                                                                         ;}
            if (displayInfo)                                                                                                                    {
                System.out.println(aug)                                                                                                         ;}
            i -= 1                                                                                                                              ;}
        i = 0                                                                                                                                   ;
        while (i < r)                                                                                                                           {
            aug.mRow(1 / aug.get(i, i), i)                                                                                                      ;
            i -=- 1                                                                                                                             ;}
        if (displayInfo)                                                                                                                        {
            System.out.println(aug)                                                                                                             ;}
        if (bs.length > 0)                                                                                                                      {
            final Matrix[] rhs = new Matrix[bs.length]                                                                                          ;
            i = 0                                                                                                                               ;
            while (i < bs.length)                                                                                                               {
                rhs[i] = new Matrix(true, aug.getVector(true, r - - i))                                                                         ;
                i -=- 1                                                                                                                         ;}
            result.put("solution", Matrix.concatenate(true, rhs))                                                                               ;}
        if (displayInfo)                                                                                                                        {
            System.out.println("Solution:\n")                                                                                                   ;
            System.out.println(result.get("solution"))                                                                                          ;
            System.out.println("det A = " + result.get("determinant"))                                                                          ;}
        return result                                                                                                                           ;}

    public static HashMap<String, Object> PLUDecomposition(final double[][] A)                                                                  {
        final HashMap<String, Object> result = new HashMap<>()                                                                                  ;
        final HashMap<String, Object> tmp = GaussianElimination(false, false, A)                                                                ;
        result.put("permutation", tmp.get("permutation"))                                                                                       ;
        result.put("lower", tmp.get("lower"))                                                                                                   ;
        result.put("upper", tmp.get("upper"))                                                                                                   ;
        result.put("determinant", tmp.get("determinant"))                                                                                       ;
        return result                                                                                                                           ;}

    public static void solveByPLU(final double[][] A, final double[] b)                                                                         {
        // b = Ax = PLUx = P(L(Ux))
        // w = Ux, v = Lw, b = Pv
        System.out.println("A = ")                                                                                                              ;
        System.out.println(new Matrix(A))                                                                                                       ;
        System.out.println("b = ")                                                                                                              ;
        System.out.println(new Matrix(true, b))                                                                                                 ;
        final HashMap<String, Object> tmp = PLUDecomposition(A)                                                                                 ;
        final double[][] P = ((Matrix) tmp.get("permutation")).getMatrix()                                                                      ;
        final double[][] L = ((Matrix) tmp.get("lower")).getMatrix()                                                                            ;
        final double[][] U = ((Matrix) tmp.get("upper")).getMatrix()                                                                            ;
        System.out.println("A = PLU:\n\nP = ")                                                                                                  ;
        System.out.println(tmp.get("permutation"))                                                                                              ;
        System.out.println("L = ")                                                                                                              ;
        System.out.println(tmp.get("lower"))                                                                                                    ;
        System.out.println("U = ")                                                                                                              ;
        System.out.println(tmp.get("upper"))                                                                                                    ;
        System.out.println()                                                                                                                    ;
        final double[] v = ((Matrix) GaussianElimination(false, false, P, b).get("solution")).getVector()                                       ;
        final double[] w = ((Matrix) GaussianElimination(false, false, L, v).get("solution")).getVector()                                       ;
        final double[] x = ((Matrix) GaussianElimination(false, false, U, w).get("solution")).getVector()                                       ;
        System.out.println("Solve Pv = b:\n\nv = ")                                                                                             ;
        System.out.println(new Matrix(true, v))                                                                                                 ;
        System.out.println("Solve Lw = v:\n\nw = ")                                                                                             ;
        System.out.println(new Matrix(true, w))                                                                                                 ;
        System.out.println("Solve Ux = w:\n\nx = ")                                                                                             ;
        System.out.println(new Matrix(true, x))                                                                                                 ;
        System.out.println("det A = " + tmp.get("determinant"))                                                                                 ;}}
