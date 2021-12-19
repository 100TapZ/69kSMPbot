/** @format */

const Event = require("../Structures/Event.js");

module.exports = new Event("ready", client => {
	console.log("Bot is ready!");
	client.user.setPresence({ activities: [{ name: 'tbe status here' }], status: 'online'  }); //status: 'online', 'idle' or 'dnd'
});