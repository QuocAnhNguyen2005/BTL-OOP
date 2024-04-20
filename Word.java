
/**
 * a word in dictionary.
 */
public class Word {
private String name;
private String meaning;
private String pronounce;
private String wordkind;
public String getWordkind() {
	return wordkind;
}
public void setWordkind(String wordkind) {
	this.wordkind = wordkind;
}	
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMeaning() {
	return meaning;
}
public void setMeaning(String meaning) {
	this.meaning = meaning;
}
public String getPronounce() {
	return pronounce;
}
public void setPronounce(String pronounce) {
	this.pronounce = pronounce;
}
public String toString() {
return	"Word[" +
            "word_target='" + name + '\'' +
            ", word_explain='" + meaning + '\'' +
            ", word_pronun='" + pronounce + '\'' +
            ']';
 }
}
