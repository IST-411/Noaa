
package noaaproject;


public class NoaaData {
        private Metadata metadata;
        private Results results[];
        
    public Metadata getMetadata(){
        return metadata;
    }
    public void setMetadata(Metadata meta){
        this.metadata = metadata;
    }
    public Results[] getResults(){
        return results;
    }
    public void setResults(Results[] results){
        this.results = results;
    }
}