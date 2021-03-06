package w279chen.cs349.a5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

public class LowerArm extends Drawable {
    private float originLeft;
    private float originRight;
    private float originTop;
    private float originBottom;

    public LowerArm(float left, float right, float top, float bottom, int pivotX, int pivotY) {
        id = idNumber;
        idNumber++;
        originLeft = left;
        originRight = right;
        originTop = top;
        originBottom = bottom;
        pivot = new Point(pivotX, pivotY);
        rotateLimit = 135;
        interactionMode = InteractionMode.ROTATING;
        defaultMode = InteractionMode.ROTATING;
        canScale = false;
    }

    @Override
    public void drawSelf(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        canvas.drawRoundRect(originLeft, originTop, originRight, originBottom, 50, 50, paint);
    }

    @Override
    public boolean pointInside(float x, float y) {
        Matrix fullMatrix = getFullMatrix();
        Matrix inverseMatrix = new Matrix();
        if (!fullMatrix.invert(inverseMatrix)) {
            return false;
        }
        float vec[] = new float[2];
        vec[0] = x;
        vec[1] = y;
        inverseMatrix.mapPoints(vec);

        if (vec[0] > originLeft && vec[0] < originRight
                && vec[1] > originTop && vec[1] < originBottom) {
            return true;
        }
        return false;
    }

}

