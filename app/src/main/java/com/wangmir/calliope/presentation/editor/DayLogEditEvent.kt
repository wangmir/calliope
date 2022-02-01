package com.wangmir.calliope.presentation.editor

import com.wangmir.calliope.domain.entities.EmotionLog

sealed class DayLogEditEvent {
    class EditTextLog(val text: String) : DayLogEditEvent()
    class SelectEmotion(val emotion: EmotionLog) : DayLogEditEvent()
    object StartRecording : DayLogEditEvent()
    object StopRecording : DayLogEditEvent()

    /**
     * if there are existing recordLog,
     * user needs to delete existing log first.
     */
    object DeleteRecordLog : DayLogEditEvent()

    /**
     * Only available when recordLog is not null
     */
    object GenerateSttLog : DayLogEditEvent()

    /**
     * Delete DayLog will cause return to the DayLog list or calendar view
     * todo: deleted DayLog and it's record file (re-stored in temp dir) will be preserved at returned view
     */
    object DeleteDayLog : DayLogEditEvent()
}
