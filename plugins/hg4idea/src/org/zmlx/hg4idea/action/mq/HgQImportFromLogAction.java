/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zmlx.hg4idea.action.mq;

import com.intellij.vcs.log.VcsFullCommitDetails;
import org.jetbrains.annotations.NotNull;
import org.zmlx.hg4idea.action.HgLogSingleCommitAction;
import org.zmlx.hg4idea.command.mq.HgQImportCommand;
import org.zmlx.hg4idea.repo.HgRepository;

public class HgQImportFromLogAction extends HgLogSingleCommitAction {
  @Override
  protected void actionPerformed(@NotNull HgRepository repository, @NotNull VcsFullCommitDetails commit) {
    String revisionHash = commit.getId().asString();
    new HgQImportCommand(repository).execute(revisionHash);
  }

  @Override
  protected boolean isEnabled(@NotNull HgRepository repository, @NotNull VcsFullCommitDetails commit) {
    return super.isEnabled(repository, commit) && !HgMqAppliedPatchAction.isAppliedPatch(repository, commit);
  }
}
