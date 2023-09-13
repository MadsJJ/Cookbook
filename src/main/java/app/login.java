package app;

public class Login {
  
  private String username; 
  private String output; 

  public void setUsername(String username){ 
    this.username = username; 
  }

  public String getUsername(){
    return username; 
  }

  public void setOutput(){
    this.output = "Invalid username"; 
  }

  public String getOutput(){
    return output; 
  }

  public boolean validateUsername(String username){
    if(((username == null))||(username.length() > 8)){
      return false; 
    }
    else{
      return true;
    } 
  }




  
  
}
