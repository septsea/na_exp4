package na4                                                                                                                                     ;

public class Matrix                                                                                                                             {
    // the number of rows
    private int row                                                                                                                             ;
    // the number of columns
    private int column                                                                                                                          ;
    private double[][] mat                                                                                                                      ;

    // initialize the matrix by filling zeros
    public Matrix(final int row, final int column)                                                                                              {
        this.row = row                                                                                                                          ;
        this.column = column                                                                                                                    ;
        this.mat = new double[this.row][this.column]                                                                                            ;
        int i = 0                                                                                                                               ;
        while (i < this.row)                                                                                                                    {
            int j = 0                                                                                                                           ;
            while (j < this.column)                                                                                                             {
                this.mat[i][j] = 0                                                                                                              ;
                j -=- 1                                                                                                                         ;}
            i -=- 1                                                                                                                             ;}}

    // initialize the matrix by a given double[][]
    public Matrix(final double[][] mat)                                                                                                         {
        this.row = mat.length                                                                                                                   ;
        this.column = mat[0].length                                                                                                             ;
        this.mat = new double[this.row][this.column]                                                                                            ;
        int i = 0                                                                                                                               ;
        while (i < this.row)                                                                                                                    {
            int j = 0                                                                                                                           ;
            while (j < this.column)                                                                                                             {
                this.mat[i][j] = mat[i][j]                                                                                                      ;
                j -=- 1                                                                                                                         ;}
            i -=- 1                                                                                                                             ;}}

    // copy a matrix
    public Matrix(final Matrix m)                                                                                                               {
        this(m.getMatrix())                                                                                                                     ;}

    // generate a row/column vector from a double[]
    public Matrix(final boolean isColumnVector, final double... vector)                                                                         {
        final int l = vector.length                                                                                                             ;
        if (isColumnVector)                                                                                                                     {
            this.row = l                                                                                                                        ;
            this.column = 1                                                                                                                     ;
            this.mat = new double[this.row][this.column]                                                                                        ;
            int i = 0                                                                                                                           ;
            while (i < l)                                                                                                                       {
                this.mat[i][0] = vector[i]                                                                                                      ;
                i -=- 1                                                                                                                         ;}}
        else                                                                                                                                    {
            this.column = l                                                                                                                     ;
            this.row = 1                                                                                                                        ;
            this.mat = new double[this.row][this.column]                                                                                        ;
            int j = 0                                                                                                                           ;
            while (j < l)                                                                                                                       {
                this.mat[0][j] = vector[j]                                                                                                      ;
                j -=- 1                                                                                                                         ;}}}

    // convert the matrix to a double[][]
    public double[][] getMatrix()                                                                                                               {
        final double[][] result = new double[this.row][this.column]                                                                             ;
        int i = 0                                                                                                                               ;
        while (i < this.row)                                                                                                                    {
            int j = 0                                                                                                                           ;
            while (j < this.column)                                                                                                             {
                result[i][j] = this.mat[i][j]                                                                                                   ;
                j -=- 1                                                                                                                         ;}
            i -=- 1                                                                                                                             ;}
        return result                                                                                                                           ;}

    // convert a 1*n or n*1 matrix to a double[]
    public double[] getVector()                                                                                                                 {
        if (this.row == 1)                                                                                                                      {
            return this.getVector(false, 0)                                                                                                     ;}
        else if (this.column == 1)                                                                                                              {
            return this.getVector(true, 0)                                                                                                      ;}
        else                                                                                                                                    {
            return null                                                                                                                         ;}}

    // extract the kth column/row (as a double[])
    public double[] getVector(final boolean isColumnVector, final int k)                                                                        {
        final double[][] m = this.getMatrix()                                                                                                   ;
        if (isColumnVector)                                                                                                                     {
            final int l = this.row                                                                                                              ;
            final double[] c = new double[l]                                                                                                    ;
            int i = 0                                                                                                                           ;
            while (i < l)                                                                                                                       {
                c[i] = m[i][k]                                                                                                                  ;
                i -=- 1                                                                                                                         ;}
            return c                                                                                                                            ;}
        else                                                                                                                                    {
            return m[k]                                                                                                                         ;}}

    // get the (i, j)-entry
    public double get(final int i, final int j)                                                                                                 {
        return this.mat[i][j]                                                                                                                   ;}

    // modify the (i, j)-entry & the previous element is returned
    public double set(final int i, final int j, final double element)                                                                           {
        final double tmp = this.mat[i][j]                                                                                                       ;
        this.mat[i][j] = element                                                                                                                ;
        return tmp                                                                                                                              ;}

    // get the size of the matrix: [row, column]
    public int[] size()                                                                                                                         {
        final int[] s = new int[2]                                                                                                              ;
        s[0] = this.row                                                                                                                         ;
        s[1] = this.column                                                                                                                      ;
        return s                                                                                                                                ;}

    // swap rows i1 & i2
    public Matrix swap(final int i1, final int i2)                                                                                              {
        this.mRowAdd(-1, i2, i1)                                                                                                                ;
        this.mRowAdd(1, i1, i2)                                                                                                                 ;
        this.mRowAdd(-1, i2, i1)                                                                                                                ;
        this.mRow(-1, i1)                                                                                                                       ;
        return this                                                                                                                             ;}

    // multiply row i by c
    public Matrix mRow(final double c, final int i)                                                                                             {
        int j = 0                                                                                                                               ;
        while (j < this.column)                                                                                                                 {
            this.mat[i][j] *= c                                                                                                                 ;
            j -=- 1                                                                                                                             ;}
        return this                                                                                                                             ;}

    // add c times row i1 to row i2
    public Matrix mRowAdd(final double c, final int i1, final int i2)                                                                           {
        int j = 0                                                                                                                               ;
        while (j < this.column)                                                                                                                 {
            this.mat[i2][j] -=- c * this.mat[i1][j]                                                                                             ;
            j -=- 1                                                                                                                             ;}
        return this                                                                                                                             ;}

    // get the transpose of the matrix
    public Matrix transpose()                                                                                                                   {
        final int row = this.column                                                                                                             ;
        final int column = this.row                                                                                                             ;
        final Matrix result = new Matrix(row, column)                                                                                           ;
        int i = 0                                                                                                                               ;
        while (i < row)                                                                                                                         {
            int j = 0                                                                                                                           ;
            while (j < column)                                                                                                                  {
                result.set(i, j, this.get(j, i))                                                                                                ;
                j -=- 1                                                                                                                         ;}
            i -=- 1                                                                                                                             ;}
        return result                                                                                                                           ;}

    // concatenate one or more matrices
    public static Matrix concatenate(final boolean horizontalConcatenation, final Matrix... ms)                                                 {
        final int l = ms.length                                                                                                                 ;
        final int[] rs = new int[l]                                                                                                             ;
        final int[] cs = new int[l]                                                                                                             ;
        int k = 0                                                                                                                               ;
        while (k < l)                                                                                                                           {
            rs[k] = ms[k].row                                                                                                                   ;
            cs[k] = ms[k].column                                                                                                                ;
            k -=- 1                                                                                                                             ;}
        if (horizontalConcatenation)                                                                                                            {
            final int tmp = rs[l - 1]                                                                                                           ;
            k = 0                                                                                                                               ;
            int s = cs[l - 1]                                                                                                                   ;
            while (k < l - 1)                                                                                                                   {
                if (rs[k] != tmp)                                                                                                               {
                    return null                                                                                                                 ;}
                s -=- cs[k]                                                                                                                     ;
                k -=- 1                                                                                                                         ;}
            final Matrix result = new Matrix(tmp, s)                                                                                            ;
            int i = 0                                                                                                                           ;
            while (i < tmp)                                                                                                                     {
                int v = 0                                                                                                                       ;
                k = 0                                                                                                                           ;
                while (k < l)                                                                                                                   {
                    final int c = cs[k]                                                                                                         ;
                    int j = 0                                                                                                                   ;
                    while (j < c)                                                                                                               {
                        result.set(i, v - - j, ms[k].get(i, j))                                                                                 ;
                        j -=- 1                                                                                                                 ;}
                    v -=- c                                                                                                                     ;
                    k -=- 1                                                                                                                     ;}
                i -=- 1                                                                                                                         ;}
            return result                                                                                                                       ;}
        else                                                                                                                                    {
            final int tmp = cs[l - 1]                                                                                                           ;
            k = 0                                                                                                                               ;
            int s = rs[l - 1]                                                                                                                   ;
            while (k < l - 1)                                                                                                                   {
                if (cs[k] != tmp)                                                                                                               {
                    return null                                                                                                                 ;}
                s -=- rs[k]                                                                                                                     ;
                k -=- 1                                                                                                                         ;}
            final Matrix result = new Matrix(s, tmp)                                                                                            ;
            int j = 0                                                                                                                           ;
            while (j < tmp)                                                                                                                     {
                int v = 0                                                                                                                       ;
                k = 0                                                                                                                           ;
                while (k < l)                                                                                                                   {
                    final int r = rs[k]                                                                                                         ;
                    int i = 0                                                                                                                   ;
                    while (i < r)                                                                                                               {
                        result.set(v - - i, j, ms[k].get(i, j))                                                                                 ;
                        i -=- 1                                                                                                                 ;}
                    v -=- rs[k]                                                                                                                 ;
                    k -=- 1                                                                                                                     ;}
                j -=- 1                                                                                                                         ;}
            return result                                                                                                                       ;}}

    // given double[][] A & double[]... bs, construct the augmented matrix [A | bs]
    // bs are COLUMN VECTORS!!
    public static Matrix augmentedMatrix(final double[][] coefficients, final double[]... rightHandSide)                                        {
        // the number of rows
        final int r = coefficients.length                                                                                                       ;
        // if there is no b
        if (rightHandSide.length == 0)                                                                                                          {
            return new Matrix(coefficients)                                                                                                     ;}
        // rightHandSide[0] is a column
        final int r2 = rightHandSide[0].length                                                                                                  ;
        if (r != r2)                                                                                                                            {
            return null                                                                                                                         ;}
        final Matrix A = new Matrix(coefficients)                                                                                               ;
        final Matrix[] bs = new Matrix[rightHandSide.length]                                                                                    ;
        int k = 0                                                                                                                               ;
        while (k < rightHandSide.length)                                                                                                        {
            bs[k] = new Matrix(true, rightHandSide[k])                                                                                          ;
            k -=- 1                                                                                                                             ;}
        return Matrix.concatenate(true, A, Matrix.concatenate(true, bs))                                                                        ;}

    // generate an identity matrix of order s
    public static Matrix identity(final int s)                                                                                                  {
        final Matrix result = new Matrix(s, s)                                                                                                  ;
        int k = 0                                                                                                                               ;
        while (k < s)                                                                                                                           {
            result.set(k, k, 1)                                                                                                                 ;
            k -=- 1                                                                                                                             ;}
        return result                                                                                                                           ;}

    @Override
    public String toString()                                                                                                                    {
        return this.toString("\n", "\t") + "\n"                                                                                                 ;}

    public String toString(final String rowSeparator, final String columnSeparator)                                                             {
        final StringBuilder sb = new StringBuilder()                                                                                            ;
        int i = 0                                                                                                                               ;
        while (i < this.row)                                                                                                                    {
            int j = 0                                                                                                                           ;
            while (j < this.column)                                                                                                             {
                sb.append(this.mat[i][j])                                                                                                       ;
                if (j < this.column - 1)                                                                                                        {
                    sb.append(columnSeparator)                                                                                                  ;}
                j -=- 1                                                                                                                         ;}
            if (i < this.row - 1)                                                                                                               {
                sb.append(rowSeparator)                                                                                                         ;}
            i -=- 1                                                                                                                             ;}
        return sb.toString()                                                                                                                    ;}}
