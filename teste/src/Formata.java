import java.util.*;
import java.text.*;
public class Formata 
{   
	public static String getDigitos(String d){
	  //elimina todos os caracteres que não sejam dígitos numéricos
	  //da string passada como parâmetro	
	  String res="";
	  int l=d.length();
	  if (!d.equals("null")){
	    for (int i=0; i<l; i++){
		  if(d.charAt(i)=='0')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='1')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='2')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='3')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='4')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='5')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='6')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='7')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='8')
		    res=res+d.charAt(i);
          else if(d.charAt(i)=='9')
		    res=res+d.charAt(i);
	    }//for
	  }//if
	  return res;
	}

	public static String dataSQL(String d){
	   //converte a string capturada no formulario
	   // para um formato que o banco de dados aceite como data
       String res=getDigitos(d);
 	   if (res.equals(""))
 	     res="null";
        else {
 	     res="'"+
 		     res.substring(2,4)+"/"+
 			 res.substring(0,2)+"/"+
 			 res.substring(4,8)+
 			 "'";
 	   }
 	   return res;
     }

    public static String dataSTR(String d){
      //converte a string capturada no banco de dados 
      //para ser colocada no formulário
	  String res=getDigitos(d);
	  if(!res.trim().equals("")){
	    res=res.substring(6,8)+"/"+
		    res.substring(4,6)+"/"+
			res.substring(0,4);
	  }//if
      return res;
	}
    
    public static String dataSTR2(String d){
        //converte a string capturada no banco de dados 
        //para ser colocada no formulário
  	  String res=getDigitos(d);
  	  if(!res.trim().equals("")){
  	    res=res.substring(6,8)+"-"+
  		    res.substring(4,6)+"-"+
  			res.substring(0,4);
  	  }//if
        return res;
  	}

    public static String dataSQL2(String d){
      //converte a data capturada no formulário para um 
      //formato que possa ser comparado com uma data do banco de dados
   	  String res=getDigitos(d);
  	  if(!res.trim().equals("")){
  	    res="'"+res.substring(4,8)+"-"+
  		    res.substring(2,4)+"-"+
  			res.substring(0,2)+"'";
  	  }//if
    	return res;
    }
    
	public static String flutuante(String f){
	  String res="";
	  int l=f.length();
      for (int i=0; i<l; i++){
		if(f.charAt(i)=='0')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='1')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='2')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='3')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='4')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='5')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='6')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='7')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='8')
		  res=res+f.charAt(i);
        else if(f.charAt(i)=='9')
		  res=res+f.charAt(i);
        else if(f.charAt(i)==',')
		  res=res+".";
	  }//for
	  if (res.equals(""))
	    res="0";
      return res;
	}

    public static String moeda(String m){
      //converte uma string capturada do banco de dados 
      //para o formato moeda para ser colocada no formulario
	  String res;
	  Locale local=new Locale("Pt", "Br");
	  NumberFormat nf;
	  DecimalFormat df=null;
	  if (m.equals(""))
	    m="0.00";
      res=m;
	  nf=NumberFormat.getCurrencyInstance(local);
	  df=(DecimalFormat) nf;
	  return df.format(Double.parseDouble(res));
	}
	
	public static String hoje(){
	  //retorna a data atual do sistema no formato dd/mm/aaaa	
	  Date hoje=new Date();
	  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	  return sdf.format(hoje);
	}

}