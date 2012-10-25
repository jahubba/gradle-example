import org.gradle.api.Project
import org.gradle.api.Plugin

class GenCodePlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('genCode') {
			def messageFile = new File('gen/message.txt')
			def code = new File('build/gen/Hello.java')
			
			outputs.dir code.parentFile
			
			doLast {
				def message = []
				messageFile.eachLine { line -> message << line }

				code.exists() ? code.delete() : code.parentFile.mkdirs()
				code << "public class Hello {\n"
				code << "	public static String getMessage() {\n"
				code << "		return \"${message.join()}\";\n";
				code << "	}\n"
				code << "}"
			}
        }
    }
}