class HelpFile{
    String[] text;
    int timesCalled;

    public HelpFile(){
        text = null;
        timesCalled = 0;
    }

    public String[] add(String toAdd){
        if (text == null){
            text =  new String[] {toAdd};
        }
        else if (text.length >= timesCalled){
            String[] newText = new String[timesCalled*2];
            int counter = 0;
            while (counter < timesCalled){
                newText[counter] = text[counter];
                ++counter;
            }
            text = newText;
            text[timesCalled] = toAdd;
        }
        else{
            text[timesCalled] = toAdd;
        }
        ++timesCalled;
        return text;
    }
}
