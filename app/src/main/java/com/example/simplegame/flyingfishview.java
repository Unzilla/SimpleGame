package com.example.simplegame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class flyingfishview extends View {
    private Bitmap fish[]=new Bitmap[2];
    private int fishX=10;
    private  int fishY;
    private  int fishspeed;
    private  int canvaswidth, canvasheight;

    private  int yellowX, yellowY, yellowspeed=16;
    private Paint yellowPaint=new Paint() ;
    private int greenX, greenY,greenspeed=20;
    private Paint greenPaint =new Paint() ;
    private int redX, redY,redspeed=25;
    private Paint redPaint =new Paint() ;
    private int score ,lifecounteroffish;

    private boolean touch= false;





    private Bitmap backgroungimage;

    private Paint scorepaint=new Paint();
    private  Bitmap life[] = new Bitmap[2];
    public flyingfishview(Context context) {
        super(context);
       fish[0]=BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]=BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backgroungimage=BitmapFactory.decodeResource(getResources(),R.drawable.background);
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);


        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);
        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);
        life[0]= BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        fishY=550;
        score=0;
        lifecounteroffish=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvaswidth=canvas.getWidth();
        canvasheight=canvas.getHeight();
        canvas.drawBitmap(backgroungimage,0,0,null);

        int minfishY=fish[0].getHeight();
        int maxfishY=canvasheight - fish[0].getHeight()*3;

        fishY= fishY +fishspeed;
       if (fishY<minfishY)
       {
           fishY=minfishY;
       }

        if (fishY>maxfishY)
        {
            fishY=maxfishY;
        }
        fishspeed=fishspeed+2;
        if (touch)
        {
           canvas.drawBitmap(fish[1],fishX, fishY,null);
           touch=false;
        }
else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);

        }


yellowX=yellowX-yellowspeed;

        if (hitballchecker(yellowX,yellowY))
        {
            score= score+10;
            yellowX=-100;
        }

if (yellowX<0)
{
    yellowX=canvaswidth+21;
    yellowY=(int)Math.floor(Math.random() *(maxfishY-minfishY)) +minfishY;
}
canvas.drawCircle(yellowX,yellowY,25,yellowPaint);






        greenX=greenX-greenspeed;

        if (hitballchecker(greenX,greenY))
        {
            score= score+20;
            greenX=-100;
        }

        if (greenX<0)
        {
          greenX=canvaswidth+21;
           greenY=(int)Math.floor(Math.random() *(maxfishY-minfishY)) +minfishY;
        }
        canvas.drawCircle(greenX,greenY,25,greenPaint);



        redX=redX-redspeed;

        if (hitballchecker(redX,redY))
        {

            redX=-100;
            lifecounteroffish--;

            if (lifecounteroffish==0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent GameOverIntent= new Intent(getContext(),GameOverActivity2.class);
                GameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                GameOverIntent.putExtra("Score" ,score);
                getContext().startActivity(GameOverIntent);
            }
        }

        if (redX<0)
        {
            redX=canvaswidth+21;
            redY=(int)Math.floor(Math.random() *(maxfishY-minfishY)) +minfishY;
        }
        canvas.drawCircle(redX,redY,25,redPaint);
        canvas.drawText("score: " + score , 20,60,scorepaint );
        for (int i=0; i<3; i++)
        {
            int x= (int)(400 + life[0].getWidth() * 1.5 * i);
            int y=30;
            if (i<lifecounteroffish)
            {
                canvas.drawBitmap(life[0],x,y,null);

            }
            else {
                canvas.drawBitmap(life[1],x,y,null);
            }
        }




    }

    public  boolean hitballchecker(int x,int y){

        if (fishX<x && x<(fishX +fish[0].getWidth())  &&  fishY<y   &&  y<(fishY+fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch=true;
            fishspeed=-27;
        }
        return  true;
    }
}
