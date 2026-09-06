package com.majruszsdifficulty.bloodmoon;

import com.majruszlibrary.command.Command;
import com.majruszlibrary.command.CommandData;
import com.majruszlibrary.text.TextHelper;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class BloodMoonCommand {
	static {
		Command.create()
			.literal( "bloodmoon" )
			.hasPermission( 4 )
			.literal( "start" )
			.execute( BloodMoonCommand::start )
			.register();

		Command.create()
			.literal( "bloodmoon" )
			.hasPermission( 4 )
			.literal( "stop" )
			.execute( BloodMoonCommand::stop )
			.register();

		Command.create()
			.literal( "bloodmoon" )
			.hasPermission( 4 )
			.literal( "time" )
			.execute( BloodMoonCommand::time )
			.register();
	}

	private static int time( CommandData data ) throws CommandSyntaxException {
		if( BloodMoonHelper.isActive() ) {
			data.source.sendSuccess( ()->TextHelper.translatable( "commands.blood_moon.active" ), false );
			return 0;
		}

		long remaining = BloodMoonConfig.TIME.from - BloodMoonHelper.getRelativeDayTime();
		if( remaining < 0 ) {
			remaining += 24000L;
		}
		long minutes = remaining / 1200;
		long seconds = ( remaining % 1200 ) / 20;
		data.source.sendSuccess( ()->TextHelper.translatable( "commands.blood_moon.next", minutes, seconds ), false );
		return 0;
	}

	private static int start( CommandData data ) throws CommandSyntaxException {
		if( BloodMoonHelper.start() ) {
			return 0;
		}

		data.source.sendFailure( TextHelper.translatable( "commands.blood_moon.cannot_start" ) );
		return -1;
	}

	private static int stop( CommandData data ) throws CommandSyntaxException {
		if( BloodMoonHelper.stop() ) {
			return 0;
		}

		data.source.sendFailure( TextHelper.translatable( "commands.blood_moon.not_started" ) );
		return -1;
	}
}
