package imageviewer.app.mock;

import imageviewer.control.Command;
import imageviewer.control.ExitImageCommand;
import imageviewer.control.NextImageCommand;
import imageviewer.control.PrevImageCommand;
import model.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import view.ImageDisplay;
import view.ImageLoader;


public class Main {
    
    public static void main(String[] args) {
        List<Image> images = new MockImageLoader().load();
        ImageDisplay imageDisplay = new MockImageDisplay();
        
        Scanner scanner = new Scanner(System.in);
        Map<String,Command> commands = new HashMap<>();
        commands.put("N", new NextImageCommand(images,imageDisplay));
        commands.put("P", new PrevImageCommand(images,imageDisplay));
        commands.put("Q", new ExitImageCommand());
 
        
        while(true){
            String input = scanner.next().toUpperCase();
            commands.get(input).execute();
        }
    }
    

    private static int bound(int index, int size) {
        return (index + size) % size;
    }
    
    public static class MockImageLoader implements ImageLoader{
        
        
        @Override
        public List<Image> load(){
            List<Image> list = new ArrayList<>();
            list.add(new Image("hola"));
            list.add(new Image("mundo"));
            list.add(new Image("bienvenido"));
            return list;

        }
        
    }
    
    public static class MockImageDisplay implements ImageDisplay{
        
        private Image image;
        
        @Override
        public void display(Image image){
            this.image= image;
            System.out.println(image.getName());
        }
        
         @Override
        public Image currentImage(){
        return image;
    }
    }
}
