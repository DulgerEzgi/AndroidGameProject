package com.VertexVerveInc.Games;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class Block extends Button
{

  private boolean isCovered; // açılmamış kare(blok)
  private boolean isMined; // mayınlı kare
	private boolean isFlagged; // potansiyel mayın olarak işaretlenmiş kare(bayrak işareti)
	private boolean isQuestionMarked; // soru işaretli kare
	private boolean isClickable; // click olayını kabul edebilen kare
	private int numberOfMinesInSurrounding; // yakındaki bloklarda kaç mayın olduğu sayısı

	public Block(Context context)
	{
		super(context);
	}
  public Block(Context context, AttributeSet attrs)
  {
		super(context, attrs);
	}

	public Block(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}// blok için default değerlerin ayarlanması
  
  
  public void setDefaults()
	{
		isCovered = true;
		isMined = false;
		isFlagged = false;
		isQuestionMarked = false;
		isClickable = true;
		numberOfMinesInSurrounding = 0;

		this.setBackgroundResource(R.drawable.beyazkare);
		setBoldFont();
	}
  
  
  // bloğun açıldığını gösteren işaretleme (kare beyazdan griye dönüyor)
  // çevredeki mayın sayısı güncelleniyor
	public void setNumberOfSurroundingMines(int number)
	{
		this.setBackgroundResource(R.drawable.square_grey);
		
		updateNumber(number);
	}

	// blok için mayın ikonu ayarlanıyor-- M yazıyor


public void setMineIcon(boolean enabled)
  {
		this.setText("M");

		if (!enabled)
		{
			this.setBackgroundResource(R.drawable.square_grey);
			this.setTextColor(Color.RED);
		}
		else
		{
			this.setTextColor(Color.BLACK);
		}
	}

	// mayını bayraklı işaretliyor--F yazıyor
	
	public void setFlagIcon(boolean enabled)
	{
		this.setText("F");

		if (!enabled)
		{
    this.setBackgroundResource(R.drawable.square_grey);
  		this.setTextColor(Color.RED);
		}
		else
		{
			this.setTextColor(Color.BLACK);
		}
	}

	// mayını soru işareti olarak işaretliyor--? yazıyor
	
	public void setQuestionMarkIcon(boolean enabled)
	{
		this.setText("?");
		
		if (!enabled)
		{
			this.setBackgroundResource(R.drawable.square_grey);
			this.setTextColor(Color.RED);
		}
		else
      {
			this.setTextColor(Color.BLACK);
		}
	}

	// bloğu açıyor

	public void setBlockAsDisabled(boolean enabled)
	{
		if (!enabled)
		{
			this.setBackgroundResource(R.drawable.square_grey);
		}
		else
		{
			this.setBackgroundResource(R.drawable.beyazkare);
		}
	}

	//tüm ikonları temizliyor
	public void clearAllIcons()
	{
		this.setText("");
	}

	// font tipini Bold yapmaya yarıyor
  private void setBoldFont()
	{
		this.setTypeface(null, Typeface.BOLD);
	}

	// bloğu açıyor
	public void OpenBlock()
	{
	
		if (!isCovered)
			return;

		setBlockAsDisabled(false);
		isCovered = false;

		// mayın olup olmadığını kontrol ediyor
		if (hasMine())
		{
			setMineIcon(false);
		}
		// etraftaki mayın sayısını belirliyor
		else
		{
			setNumberOfSurroundingMines(numberOfMinesInSurrounding);
		}
    
    }

  // etraftaki mayın sayısını text olarak yazıyor
	public void updateNumber(int text)
	{
		if (text != 0)
		{
			this.setText(Integer.toString(text));

			// her bir mayın numarası için farklı renk atıyor--1 ise mavi, 2 ise yeşil, 3 ise kırmızı
			// 0 tane mayını atlıyor
			switch (text)
			{
				case 1:
					this.setTextColor(Color.BLUE);
					break;
				case 2:
					this.setTextColor(Color.rgb(0, 100, 0));
					break;
				case 3:
					this.setTextColor(Color.RED);
					break;
				case 4:
					this.setTextColor(Color.rgb(85, 26, 139));
					break;
				case 5:
					this.setTextColor(Color.rgb(139, 28, 98));
					break;
				case 6:
					this.setTextColor(Color.rgb(238, 173, 14));
					break;
				case 7:
					this.setTextColor(Color.rgb(47, 79, 79));
          break;
  			case 8:
					this.setTextColor(Color.rgb(71, 71, 71));
					break;
				case 9: 
					this.setTextColor(Color.rgb(205, 205, 0));
					break;
			}
		}
	}
// bir bloğu mayınlı algılıyor
  public void plantMine()
	{
		isMined = true;
	}

	// mayına basıldığında iconu ve rengini değiştiriyor
	
	public void triggerMine()
	{
		setMineIcon(true);
		this.setTextColor(Color.RED);
	}

	// blok hala kapalı 
	public boolean isCovered()
	{
		return isCovered;
	}

	// bloklar mayına sahip ise
	public boolean hasMine()
	{
		return isMined;
	}

	// etraftaki mayın sayısı
	public void setNumberOfMinesInSurrounding(int number)
	{
		numberOfMinesInSurrounding = number;
	}
  
  // etraftaki mayın sayısını alıyor
  public int getNumberOfMinesInSorrounding()
	{
		return numberOfMinesInSurrounding;
	}

	// blok bayraklı işaretlenmişse
	public boolean isFlagged()
	{
		return isFlagged;
	}


	public void setFlagged(boolean flagged)
	{
		isFlagged = flagged;
	}

	// blok soru işaretliyle işaretlenmişse
	public boolean isQuestionMarked()
	{
		return isQuestionMarked;
	}

	
	public void setQuestionMarked(boolean questionMarked)
	{
		isQuestionMarked = questionMarked;
	}
  
  // kare tıklanabiliyorsa
  public boolean isClickable()
	{
		return isClickable;
	}

	// kare tıklanamıyorsa
	public void setClickable(boolean clickable)
	{
		isClickable = clickable;
	}
}
          
