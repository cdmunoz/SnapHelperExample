package co.cdmunoz.snaphelperexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.cdmunoz.snaphelperexample.data.model.Candy;
import java.util.ArrayList;
import java.util.List;

public class CandiesAdapter extends RecyclerView.Adapter<CandiesAdapter.CandyHolder> {

  private List<Candy> candies = new ArrayList<>();

  @Override public CandyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candies_list, parent, false);
    return new CandyHolder(itemView);
  }

  @Override public void onBindViewHolder(CandyHolder holder, int position) {
    holder.bindCandy(candies.get(position));
  }

  @Override public int getItemCount() {
    return candies.size();
  }

  public void setCandies(List<Candy> candies) {
    this.candies = candies;
    notifyDataSetChanged();
  }

  class CandyHolder extends RecyclerView.ViewHolder {

    private View itemView;
    @BindView(R.id.candyImage) ImageView candyImage;
    @BindView(R.id.candyName) TextView candyName;

    CandyHolder(View itemView) {
      super(itemView);
      this.itemView = itemView;
      ButterKnife.bind(this, itemView);
    }

    void bindCandy(Candy candy) {
      candyImage.setImageResource(candy.getCandyResource());
      candyName.setText(candy.getCandyName());
    }
  }
}
