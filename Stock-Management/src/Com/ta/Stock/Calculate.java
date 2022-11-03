package Com.ta.Stock;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;
import javax.swing.JOptionPane;

   public class Calculate   
   {
    static String input;
    static double numberOfShares;
    static double purchasePrice;
    static double purchaseCommission;
    static double salesPrice;
    static double salesCommission;
    static double profit;
    static double amount;
    static String UserName;
    static String Password;
    static String stock;
	static double currentbalance;
    static DecimalFormat df = new DecimalFormat("0.00");
    
    static int AccountBalance=2000; 
    static double Fund=2000;
    static int count=1;
    
	   
    
	static void Choose() throws IOException
	{
		  Properties prob=new Properties();
		  FileInputStream finn=new FileInputStream("C:\\New folder\\Stock-Management\\src\\Com\\ta\\Stock\\AvailableStocks.properties");
		  prob.load(finn);
	      input = JOptionPane.showInputDialog("Enter the Product Stock you want to purchase?");
	        stock = input;
	        if(prob.keySet().contains(stock))
	        {
	       
	     		
	     		String Available = prob.getProperty(String.valueOf(stock));
	     		
	     		int AvailableStock=Integer.parseInt(Available);
	     		
	        	input = JOptionPane.showInputDialog("How many shares did you purchase?");
    	  		numberOfShares = Integer.parseInt(input);
	     		
    	  		if(numberOfShares<=AvailableStock)
    	  		{
    	  			input = JOptionPane.showInputDialog("What was the price per share?");
  					purchasePrice = Double.parseDouble(input);

  					Commision();
    	  		}
    	  		else
    	  		{
    	  			JOptionPane.showMessageDialog(null,"Stocks Insufficient", "Error", JOptionPane.INFORMATION_MESSAGE);
    	  			JOptionPane.showMessageDialog(null,"Available Stocks :"+AvailableStock,"Error", JOptionPane.INFORMATION_MESSAGE);
    	  			
    	  			Choose();
    	  		}
	     		
	           
	        }
	         else if(stock.isBlank())
	         {
		       
	        	 JOptionPane.showMessageDialog(null,"Stock name is Blank", "Stock Not Found", JOptionPane.INFORMATION_MESSAGE);
		     
	        	 System.exit(0);

	          }
	        else
	          {
	        	JOptionPane.showMessageDialog(null,"Invalid Stock name", "Stock Not Found", JOptionPane.INFORMATION_MESSAGE);
	        	
	        	System.exit(0);
	          }
	         
	         
	        }
    
            static void sellchoice() throws IOException
             {
            	 input = JOptionPane.showInputDialog("Do You Want to  sell");
     	           String Choice = String.valueOf(input);
     	        
            	 if(Choice.equalsIgnoreCase("Yes"))
        	        {
        	        	
        	        	     sell();
        	        	 	
        	        }
        	        else if(Choice.equalsIgnoreCase("NO"))
        	        {
        	        System.exit(0);	
        	        }
                 
        	        
        	        else if(Choice.isBlank())
        	        {
        	        	JOptionPane.showMessageDialog(null,"Choice is Blank", "Error", JOptionPane.INFORMATION_MESSAGE);
        	        	sellchoice();
        	        }
        	        else if(!Choice.equalsIgnoreCase("yes") && !Choice.equalsIgnoreCase("No"))
        	        {
        	        	JOptionPane.showMessageDialog(null,"Choice Not Valid", "Error", JOptionPane.INFORMATION_MESSAGE);
        	        	sellchoice();
        	        }
        	        else
        	        {
        	        	JOptionPane.showMessageDialog(null,"Invalid", "Error", JOptionPane.INFORMATION_MESSAGE);
        	        	sellchoice();
        	        }
               
               
                   
             }
    
      static void Commision()
      {
        
         input = JOptionPane.showInputDialog("What was the purchase commission fee?");
         purchaseCommission = Double.parseDouble(input);
         amount=(numberOfShares*purchasePrice)+purchaseCommission;
     
                           try
                           {
                       if(amount<=AccountBalance)
                                  {
                                	   AccountBalance-=amount;
                                       JOptionPane.showMessageDialog(null, "Your Account Balance Rs." + df.format(AccountBalance), "Stock Profit App", JOptionPane.INFORMATION_MESSAGE);
                                       sellchoice();
                                  }
                           
                                 else
                                 {

                     	        	JOptionPane.showMessageDialog(null,"Insufficient Balace", "Error", JOptionPane.INFORMATION_MESSAGE);
                     	        	JOptionPane.showMessageDialog(null,"Enter the Purchase limit less than or Equal of Rs."+df.format(AccountBalance), "Error", JOptionPane.INFORMATION_MESSAGE);
                     	        	Choose();
                                 }
                           }
                           catch(Exception e)
                           {
               	        	JOptionPane.showMessageDialog(null,"Invalid value", "Login", JOptionPane.INFORMATION_MESSAGE);

                           }
                                 
        }
         
          	static void sell() throws IOException
          	{
                 
          	input = JOptionPane.showInputDialog("How much did you sell each share for?");
          	salesPrice = Double.parseDouble(input);
         
          			input = JOptionPane.showInputDialog("What was the selling commission fee?");
          			salesCommission = Double.parseDouble(input);
         
          			profit =((numberOfShares * salesPrice) +salesCommission)-amount; 
         
        
        
          				if(profit > 0 )
          				{
          					JOptionPane.showMessageDialog(null, "Your Stock Profit  Rs." + df.format(profit), "Stock Profit App", JOptionPane.INFORMATION_MESSAGE);

          					AccountBalance+=profit;
          					JOptionPane.showMessageDialog(null, "Your Account Balance After Profit  Rs." + df.format(AccountBalance), "Stock Profit App", JOptionPane.INFORMATION_MESSAGE);

          					Choose();
          					System.exit(0);
            
          				} 
          				else if(profit<0)
          				{
          					JOptionPane.showMessageDialog(null, "Your Stock after Loss  Rs." + df.format(profit), "Stock Profit App", JOptionPane.INFORMATION_MESSAGE);

          					AccountBalance-=profit;
          					JOptionPane.showMessageDialog(null, "Your Account Balance After Loss  Rs." + df.format(AccountBalance), "Stock Profit App", JOptionPane.INFORMATION_MESSAGE);
	                        if(AccountBalance<0)    
	                        {
	                        	Accoutstatus();
	                        	
	                        }
	                        else
	                        {
	                        	Choose();
	                        	System.exit(0);
	                        }
          				}
        
                }
      
       
      static void Accoutstatus() throws IOException
      {
    
    	  
    	   if(AccountBalance>0 && count==1)
          {
            JOptionPane.showMessageDialog(null, "Low Balance Rs." + df.format(AccountBalance), "Stock Profit App", JOptionPane.INFORMATION_MESSAGE);

          	AccountBalance+=Fund;
           
          	JOptionPane.showMessageDialog(null, "Bonus Fund Credicted Rs." + df.format(AccountBalance), "Stock Profit App", JOptionPane.INFORMATION_MESSAGE);

          	
          	count++;
       	Choose();
          }
          else if(AccountBalance>0 && count==2)
          {
              JOptionPane.showMessageDialog(null, "Insufficient Account Fund Rs." + df.format(AccountBalance), "Stock Profit App", JOptionPane.INFORMATION_MESSAGE);
              JOptionPane.showMessageDialog(null,"Thanks", "End", JOptionPane.INFORMATION_MESSAGE);
     	       System.exit(0);
           }
          
          System.exit(0);
      }
    
}