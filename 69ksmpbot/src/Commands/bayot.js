/**@format */
//bayot means gay

const Discord = require("discord.js")
const Command = require("../Structures/Command.js")

module.exports = new Command({
        name: "bayot",
        description: "bayot ka",
        permission: "SEND_MESSAGES",
        type: "TEXT",
        timeout: 10000,
    async run(message, args, client) {
        const member = message.mentions.users.first() || message.author

        const rng = Math.floor(Math.random() * 101);

        const embed = new Discord.MessageEmbed()
        .setTitle(`bayot ka o dili`)
        .setDescription(`${member.username} is ` + rng + "% bayot")
        .setColor("RED")

        message.channel.send({embeds: [embed]})     
    }
})
