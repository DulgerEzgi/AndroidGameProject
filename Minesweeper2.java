// mesaj gösterme
  	showDialog("You won in " + Integer.toString(secondsPassed) + " seconds!", 1000, false, true);
	}

	private void finishGame(int currentRow, int currentColumn)
	{
		isGameOver = true; // oyunu bitti olarak işaretleme
		stopTimer(); 
		isTimerStarted = false;
		btnSmile.setBackgroundResource(R.drawable.sad1);

		// tüm mayınları gösterme
		// kareleri disable etme
		for (int row = 1; row < numberOfRowsInMineField + 1; row++)
		{
			for (int column = 1; column < numberOfColumnsInMineField + 1; column++)
			{
				// kareyi disable etme
				blocks[row][column].setBlockAsDisabled(false);
				
				// kare mayına sahip ve bayraklanmamış
				if (blocks[row][column].hasMine() && !blocks[row][column].isFlagged())
				{
					// mayın ikonu koyma
					blocks[row][column].setMineIcon(false);
				}

				// kare bayraklanmış ama mayını yok
				if (!blocks[row][column].hasMine() && blocks[row][column].isFlagged())
				{
					// bayrak ikonu koyma
					blocks[row][column].setFlagIcon(false);
				}
        // kareyi bayraklama
  			if (blocks[row][column].isFlagged())
				{
					// kareyi disable etme
					blocks[row][column].setClickable(false);
				}
			}
		}
    
		// mayını tetikleme
		blocks[currentRow][currentColumn].triggerMine();

		// mesaj gösterme
		showDialog("You tried for " + Integer.toString(secondsPassed) + " seconds!", 1000, false, false);
	}
  private void setMines(int currentRow, int currentColumn)
  {
		// kullanıcının tıkladığı alanın dışına mayın yerleştirme
		Random rand = new Random();
		int mineRow, mineColumn;

		for (int row = 0; row < totalNumberOfMines; row++)
		{
			mineRow = rand.nextInt(numberOfColumnsInMineField);
			mineColumn = rand.nextInt(numberOfRowsInMineField);
			if ((mineRow + 1 != currentColumn) || (mineColumn + 1 != currentRow))
			{
				if (blocks[mineColumn + 1][mineRow + 1].hasMine())
				{
					row--; // mayın zaten o karede var, aynı kare için tekrarlanmaz
				}
				// konuma mayın yerleştirme
				blocks[mineColumn + 1][mineRow + 1].plantMine();
			}
			// kullanıcının tıkladığı alanın dışına çıkma
			else
			{
				row--;
			}
		}
    int nearByMineCount;

  	// etraftaki karelerdeki mayınları sayma
		for (int row = 0; row < numberOfRowsInMineField + 2; row++)
		{
			for (int column = 0; column < numberOfColumnsInMineField + 2; column++)
			{
				// her bir kare yakınındaki mayın sayısını bulur
				nearByMineCount = 0;
				if ((row != 0) && (row != (numberOfRowsInMineField + 1)) && (column != 0) && (column != (numberOfColumnsInMineField + 1)))
				{
					// yakındaki tüm blokları kontrol etme
					for (int previousRow = -1; previousRow < 2; previousRow++)
					{
						for (int previousColumn = -1; previousColumn < 2; previousColumn++)
						{
							if (blocks[row + previousRow][column + previousColumn].hasMine())
							{
								// mayın bulununca mayın sayısının counter'ını arttırma
								nearByMineCount++;
							}
						}
					}
					blocks[row][column].setNumberOfMinesInSurrounding(nearByMineCount);
				}
				// kenar satırları için (0. veya sonuncu satır veya sütun için)
				// count'u 9 olarak ayarla ve açılmış olarak işaretle
				else
				{
					blocks[row][column].setNumberOfMinesInSurrounding(9);
					blocks[row][column].OpenBlock();
				}
			}
      }
  }
	private void rippleUncover(int rowClicked, int columnClicked)
	{
		// bayraklı veya mayını satırları açma!
		if (blocks[rowClicked][columnClicked].hasMine() || blocks[rowClicked][columnClicked].isFlagged())
		{
			return;
		}
    // tıklanmış bloğu açar
  	blocks[rowClicked][columnClicked].OpenBlock();

		// eğer tıklanmış blok mayınların yanındaysa daha fazla açma
		if (blocks[rowClicked][columnClicked].getNumberOfMinesInSorrounding() != 0 )
		{
			return;
		}
