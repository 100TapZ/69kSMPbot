/** @format */

const Command = require("../Structures/Command.js");

module.exports = new Command({
	name: "ip",
	description: "Shows the SMP ip address",
	type: "BOTH",
	slashCommandOptions: [],
	permission: "SEND_MESSAGES",
	async run(message, args, client) {
		message.reply("The ip of my smp is (smp ip)");
	}
});