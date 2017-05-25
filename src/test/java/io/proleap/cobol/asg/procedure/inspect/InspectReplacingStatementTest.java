package io.proleap.cobol.asg.procedure.inspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfterPhrase;
import io.proleap.cobol.asg.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Replacing;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeading;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeadings;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingCharacters;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InspectReplacingStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/inspect/InspectReplacingStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InspectReplacingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final InspectStatement inspectStatement = (InspectStatement) procedureDivision.getStatements().get(0);
			assertNotNull(inspectStatement);
			assertEquals(StatementTypeEnum.INSPECT, inspectStatement.getStatementType());
			assertEquals(InspectStatement.InspectType.REPLACING, inspectStatement.getInspectType());

			{
				final Replacing replacing = inspectStatement.getReplacing();
				assertEquals(1, replacing.getCharacters().size());
				assertEquals(1, replacing.getAllLeadings().size());

				{
					final ReplacingCharacters characters = replacing.getCharacters().get(0);
					assertEquals(1, characters.getBeforeAfterPhrases().size());

					{
						final BeforeAfterPhrase beforeAfter = characters.getBeforeAfterPhrases().get(0);
						assertEquals(BeforeAfterPhrase.BeforeAfterType.AFTER, beforeAfter.getBeforeAfterType());
					}
				}

				{
					final ReplacingAllLeadings allLeadings = replacing.getAllLeadings().get(0);
					assertEquals(ReplacingAllLeadings.ReplacingAllLeadingsType.FIRST, allLeadings.getReplacingAllLeadingsType());
					assertEquals(1, allLeadings.getAllLeadings().size());

					{
						final ReplacingAllLeading allLeading = allLeadings.getAllLeadings().get(0);

						assertNotNull(allLeading.getPatternDataItemValueStmt());
						assertEquals("B", allLeading.getPatternDataItemValueStmt().getValue());
						assertEquals(1, allLeading.getBeforeAfterPhrases().size());

						{
							final BeforeAfterPhrase beforeAfter = allLeading.getBeforeAfterPhrases().get(0);
							assertEquals(BeforeAfterPhrase.BeforeAfterType.BEFORE, beforeAfter.getBeforeAfterType());
						}
					}
				}
			}
		}
	}
}