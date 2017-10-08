package co.cdmunoz.snaphelperexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.horizontalSnapImageView) ImageView horizontalSnap;
  @BindView(R.id.horizontalSnapTitle) TextView horizontalSnapTitle;
  @BindView(R.id.verticalSnapTopImageView) ImageView verticalSnapTop;
  @BindView(R.id.verticalSnapTopTitle) TextView verticalSnapTitleTop;
  @BindView(R.id.verticalSnapBottomImageView) ImageView verticalSnapBottom;
  @BindView(R.id.verticalSnapBottomTitle) TextView verticalSnapTitleBottom;

  private View.OnClickListener horizontalSnapListerner = new View.OnClickListener() {
    @Override public void onClick(View view) {
      startActivity(new Intent(MainActivity.this, HorizontalSnapActivity.class));
    }
  };

  private View.OnClickListener verticalTopSnapListerner = new View.OnClickListener() {
    @Override public void onClick(View view) {
      Intent intent = new Intent(MainActivity.this, VerticalSnapActivity.class);
      intent.putExtra(VerticalSnapActivity.EXTRA_PARAM_SNAP_TYPE, "TOP");
      startActivity(intent);
    }
  };

  private View.OnClickListener verticalBottomSnapListerner = new View.OnClickListener() {
    @Override public void onClick(View view) {
      Intent intent = new Intent(MainActivity.this, VerticalSnapActivity.class);
      intent.putExtra(VerticalSnapActivity.EXTRA_PARAM_SNAP_TYPE, "BOTTOM");
      startActivity(intent);
    }
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    horizontalSnap.setOnClickListener(horizontalSnapListerner);
    horizontalSnapTitle.setOnClickListener(horizontalSnapListerner);
    verticalSnapTop.setOnClickListener(verticalTopSnapListerner);
    verticalSnapTitleTop.setOnClickListener(verticalTopSnapListerner);
    verticalSnapBottom.setOnClickListener(verticalBottomSnapListerner);
    verticalSnapTitleBottom.setOnClickListener(verticalBottomSnapListerner);
  }
}
