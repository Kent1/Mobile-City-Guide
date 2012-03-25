package be.ac.umons.gl.mobilecityguide.route;

import java.util.Iterator;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

/**
 * @author Quentin Loos
 */
public class RouteOverlay extends Overlay {
  private final Route route;

  public RouteOverlay(Route route) {
    this.route = route;
  }

  @Override
  public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
    Projection projection = mapView.getProjection();

    if (!shadow) {

      Path path = new Path();

      Paint paint = new Paint();
      paint.setAntiAlias(true);
      paint.setDither(true);
      paint.setColor(Color.BLUE);
      paint.setStyle(Paint.Style.STROKE);
      paint.setStrokeWidth(5);
      paint.setAlpha(120);

      Point point = new Point();

      List<GeoPoint> geoPoints = route.decodePoly();

      Iterator<GeoPoint> iter = geoPoints.listIterator();

      if (iter.hasNext()) {
        projection.toPixels(iter.next(), point);
        path.moveTo(point.x, point.y);

        while (iter.hasNext()) {
          projection.toPixels(iter.next(), point);
          path.lineTo(point.x, point.y);
        }

        canvas.drawPath(path, paint);

      }
    }

    return super.draw(canvas, mapView, shadow, when);
  }
}