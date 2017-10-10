package co.cdmunoz.snaphelperexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {

  @BindView(R.id.horizontalSnapImageView) lateinit var horizontalSnap: ImageView
  @BindView(R.id.horizontalSnapTitle) lateinit var horizontalSnapTitle: TextView
  @BindView(R.id.verticalSnapTopImageView) lateinit var verticalSnapTop: ImageView
  @BindView(R.id.verticalSnapTopTitle) lateinit var verticalSnapTitleTop: TextView
  @BindView(R.id.verticalSnapBottomImageView) lateinit var verticalSnapBottom: ImageView
  @BindView(R.id.verticalSnapBottomTitle) lateinit var verticalSnapTitleBottom: TextView

  private val horizontalSnapListerner = View.OnClickListener {
    startActivity(Intent(this@MainActivity, HorizontalSnapActivity::class.java))
  }

  private val verticalTopSnapListerner = View.OnClickListener {
    val intent = Intent(this@MainActivity, VerticalSnapActivity::class.java)
    intent.putExtra(VerticalSnapActivity.EXTRA_PARAM_SNAP_TYPE, "TOP")
    startActivity(intent)
  }

  private val verticalBottomSnapListerner = View.OnClickListener {
    val intent = Intent(this@MainActivity, VerticalSnapActivity::class.java)
    intent.putExtra(VerticalSnapActivity.EXTRA_PARAM_SNAP_TYPE, "BOTTOM")
    startActivity(intent)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    ButterKnife.bind(this)

    horizontalSnap.setOnClickListener(horizontalSnapListerner)
    horizontalSnapTitle.setOnClickListener(horizontalSnapListerner)
    verticalSnapTop.setOnClickListener(verticalTopSnapListerner)
    verticalSnapTitleTop.setOnClickListener(verticalTopSnapListerner)
    verticalSnapBottom.setOnClickListener(verticalBottomSnapListerner)
    verticalSnapTitleBottom.setOnClickListener(verticalBottomSnapListerner)
  }
}