package com.example.car_doctor;

import java.util.ArrayList;

public class Liste_parent
{
	private String name;
	private String text1;
	private String text2;
	
	private int etat;
	  //etat=0 date de maintenance encore loin
      //etat=1 date de maintenance proche
      //etat=2 date de maintenance depassé
	private ArrayList<Liste_enfant> children;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getText1()
	{
		return text1;
	}
	
	public void setText1(String text1)
	{
		this.text1 = text1;
	}
	
	public String getText2()
	{
		return text2;
	}
	
	public void setText2(String text2)
	{
		this.text2 = text2;
	}
	
	public int getEtat()
	{
		return this.etat;
	}
	
	public void setEtat(int etat)
	{
		this.etat = etat;
	}
	
	public boolean isGreen()
	{
		return (this.etat==0);
	}
	
	public boolean isWarning()
	{
		return (this.etat==1);
	}
	public boolean isRed()
	{
		return (this.etat==2);
	}
	
	
	public ArrayList<Liste_enfant> getChildren()
	{
		return children;
	}
	
	public void setChildren(ArrayList<Liste_enfant> children)
	{
		this.children = children;
	}
}
